package click.seichi.gigantic.skill

import click.seichi.gigantic.Gigantic
import click.seichi.gigantic.animation.SkillAnimations
import click.seichi.gigantic.belt.Belt
import click.seichi.gigantic.cache.key.Keys
import click.seichi.gigantic.cache.manipulator.catalog.CatalogPlayerCache
import click.seichi.gigantic.extension.*
import click.seichi.gigantic.message.messages.PlayerMessages
import click.seichi.gigantic.player.LockedFunction
import click.seichi.gigantic.popup.PopUpParameters
import click.seichi.gigantic.popup.SkillPops
import click.seichi.gigantic.sound.sounds.SkillSounds
import click.seichi.gigantic.util.Random
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.function.Consumer

/**
 * @author tar0ss
 *
 * TODO スキルレベルの概念を追加する
 */
object Skills {

    val MINE_BURST = object : Skill {

        val duration = SkillParameters.MINE_BURST_DURATION
        val coolTime = SkillParameters.MINE_BURST_COOLTIME

        override fun findInvokable(player: Player): Consumer<Player>? {
            if (!LockedFunction.MINE_BURST.isUnlocked(player)) return null
            val mineBurst = player.find(CatalogPlayerCache.MINE_BURST) ?: return null
            if (!mineBurst.canStart()) return null
            return Consumer { p ->
                mineBurst.coolTime = coolTime
                mineBurst.duration = duration
                mineBurst.onStart {
                    p.removePotionEffect(PotionEffectType.FAST_DIGGING)
                    p.addPotionEffect(PotionEffect(PotionEffectType.FAST_DIGGING, 100, 2, true, false))
                    SkillSounds.MINE_BURST_ON_FIRE.play(p.location)
                    p.getOrPut(Keys.BELT).wear(p)
                }.onFire {
                    p.getOrPut(Keys.BELT).wear(p, false)
                }.onCompleteFire {
                    p.getOrPut(Keys.BELT).wear(p)
                }.onCooldown {
                    p.getOrPut(Keys.BELT).wear(p, false)
                }.onCompleteCooldown {
                    p.getOrPut(Keys.BELT).wear(p)
                }.start()
            }
        }
    }

    val FLASH = object : Skill {

        val transparentMaterialSet = setOf(
                Material.AIR,
                Material.CAVE_AIR,
                Material.VOID_AIR,
                Material.WATER,
                Material.LAVA
        )

        val maxDistance = 50

        val coolTime = SkillParameters.FLASH_COOLTIME

        override fun findInvokable(player: Player): Consumer<Player>? {
            if (!LockedFunction.FLASH.isUnlocked(player)) return null
            val flash = player.find(CatalogPlayerCache.FLASH) ?: return null
            if (!flash.canStart()) return null
            return Consumer { p ->
                flash.coolTime = coolTime
                flash.onStart {
                    val tpLocation = p.getTargetBlock(transparentMaterialSet, maxDistance).let { block ->
                        if (block.type == Material.AIR) return@let null
                        var nextBlock = block ?: return@let null
                        while (!nextBlock.isSurface) {
                            nextBlock = nextBlock.getRelative(BlockFace.UP)
                        }
                        nextBlock.centralLocation.add(0.0, 1.75, 0.0).apply {
                            direction = p.location.direction
                        }
                    }
                    if (tpLocation != null) {
                        SkillAnimations.FLASH_FIRE.start(p.location)
                        p.teleport(tpLocation)
                        SkillAnimations.FLASH_FIRE.start(p.location)
                        SkillSounds.FLASH_FIRE.play(p.location)
                    } else {
                        SkillSounds.FLASH_MISS.play(p.location)
                        flash.isCancelled = true
                    }
                    p.getOrPut(Keys.BELT).wear(p)
                }.onCooldown {
                    p.getOrPut(Keys.BELT).wear(p, false)
                }.onCompleteCooldown {
                    p.getOrPut(Keys.BELT).wear(p)
                }.start()
            }
        }

    }

    val HEAL = object : Skill {
        override fun findInvokable(player: Player): Consumer<Player>? {
            val block = player.remove(Keys.HEAL_SKILL_BLOCK) ?: return null
            if (!LockedFunction.HEAL.isUnlocked(player)) return null
            if (SkillParameters.HEAL_PROBABILITY_PERCENT < Random.nextInt(100)) return null
            return Consumer { p ->
                p.manipulate(CatalogPlayerCache.HEALTH) {
                    if (it.isMaxHealth()) return@manipulate
                    val wrappedAmount = it.increase(it.max.div(100L).times(SkillParameters.HEAL_AMOUNT_PERCENT))
                    SkillAnimations.HEAL.start(p.location.clone().add(0.0, 1.7, 0.0))
                    SkillPops.HEAL(wrappedAmount).pop(block.centralLocation.add(0.0, PopUpParameters.HEAL_SKILL_DIFF, 0.0))
                    PlayerMessages.HEALTH_DISPLAY(it).sendTo(p)
                }
            }
        }

    }

    val SWITCH = object : Skill {
        override fun findInvokable(player: Player): Consumer<Player>? {
            if (!LockedFunction.SWITCH.isUnlocked(player)) return null
            return Consumer { p ->
                var current: Belt? = null
                p.manipulate(CatalogPlayerCache.BELT_SWITCHER) {
                    current = it.current
                    it.switch()
                }
                val nextBelt = p.getOrPut(Keys.BELT)
                if (current == nextBelt) return@Consumer
                nextBelt.wear(p)
                SkillSounds.SWITCH.playOnly(p)

            }
        }

    }


    val TERRA_DRAIN = object : Skill {

        override fun findInvokable(player: Player): Consumer<Player>? {
            val block = player.remove(Keys.TERRA_DRAIN_SKILL_BLOCK) ?: return null
            if (player.gameMode != GameMode.SURVIVAL) return null
            if (!Gigantic.TREES.contains(block.type)) return null
            return Consumer { p ->
                val miner = Miner(
                        block,
                        p,
                        Gigantic.TREES,
                        SkillParameters.TERRA_DRAIN_FACE_SET,
                        Miner.ConstructionType.RADIUS,
                        maxRadius = SkillParameters.TERRA_DRAIN_MAX_RADIUS,
                        nextBreakDelay = SkillParameters.TERRA_DRAIN_DELAY
                )
                miner.mineRelations { target ->
                    SkillAnimations.TERRA_DRAIN_TREE.start(target.centralLocation)
                    SkillSounds.TERRA_DRAIN.play(target.centralLocation)
                    player.manipulate(CatalogPlayerCache.HEALTH) {
                        if (it.isMaxHealth()) return@manipulate
                        val percent = when {
                            target.isLog -> SkillParameters.TERRA_DRAIN_LOG_HEAL_PERCENT
                            target.isLeaves -> SkillParameters.TERRA_DRAIN_LEAVES_HEAL_PERCENT
                            else -> 0.0
                        }
                        val wrappedAmount = it.increase(it.max.div(100.0).times(percent).toLong())
                        if (wrappedAmount > 0) {
                            SkillPops.HEAL(wrappedAmount).pop(target.centralLocation)
                            PlayerMessages.HEALTH_DISPLAY(it).sendTo(player)
                        }
                    }
                }
                SkillAnimations.TERRA_DRAIN_HEAL.start(p.location.clone().add(0.0, 1.7, 0.0))
            }
        }
    }

}