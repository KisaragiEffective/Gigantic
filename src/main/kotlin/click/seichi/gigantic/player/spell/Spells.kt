package click.seichi.gigantic.player.spell

import click.seichi.gigantic.acheivement.Achievement
import click.seichi.gigantic.animation.animations.SpellAnimations
import click.seichi.gigantic.breaker.spells.Apostol
import click.seichi.gigantic.cache.key.Keys
import click.seichi.gigantic.cache.manipulator.catalog.CatalogPlayerCache
import click.seichi.gigantic.extension.*
import click.seichi.gigantic.message.messages.PlayerMessages
import click.seichi.gigantic.player.Invokable
import click.seichi.gigantic.popup.pops.PopUpParameters
import click.seichi.gigantic.popup.pops.SpellPops
import click.seichi.gigantic.sound.sounds.SpellSounds
import click.seichi.gigantic.util.Random
import org.bukkit.entity.Player
import java.util.function.Consumer

/**
 * @author tar0ss
 */
object Spells {

    // 読み:ステラ・クレア
    val STELLA_CLAIR = object : Invokable {
        override fun findInvokable(player: Player): Consumer<Player>? {
            if (!Achievement.SPELL_STELLA_CLAIR.isGranted(player)) return null
            if (SpellParameters.STELLA_CLAIR_PROBABILITY_PERCENT < Random.nextInt(100)) return null
            player.find(CatalogPlayerCache.MANA)?.let {
                if (it.isMaxMana()) return null
            } ?: return null
            return Consumer { p ->
                val block = player.getOrPut(Keys.BREAK_BLOCK) ?: return@Consumer
                var wrappedAmount = 0.toBigDecimal()

                p.manipulate(CatalogPlayerCache.MANA) {
                    wrappedAmount = it.increase(it.max.div(100.toBigDecimal()).times(SpellParameters.STELLA_CLAIR_AMOUNT_PERCENT.toBigDecimal()))
                }

                SpellAnimations.STELLA_CLAIR.absorb(p, block.centralLocation)
                SpellPops.STELLA_CLAIR(wrappedAmount).pop(block.centralLocation.add(0.0, PopUpParameters.STELLA_CLAIR_SKILL_DIFF, 0.0))
                SpellSounds.STELLA_CLAIR.play(block.centralLocation)

                player.find(CatalogPlayerCache.MANA)?.let {
                    PlayerMessages.MANA_DISPLAY(it).sendTo(p)
                }

                player.offer(Keys.IS_UPDATE_PROFILE, true)
                player.getOrPut(Keys.BAG).carry(player)
            }
        }
    }

    val APOSTOL = object : Invokable {
        override fun findInvokable(player: Player): Consumer<Player>? {
            val mana = player.find(CatalogPlayerCache.MANA) ?: return null
            if (!mana.hasMana(0.toBigDecimal())) return null
            return Consumer { p ->
                val b = player.getOrPut(Keys.BREAK_BLOCK) ?: return@Consumer
                Apostol().cast(p, b)
            }
        }
    }

}