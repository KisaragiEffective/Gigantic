package click.seichi.gigantic.button.buttons

import click.seichi.gigantic.button.Button
import click.seichi.gigantic.cache.manipulator.catalog.CatalogPlayerCache
import click.seichi.gigantic.extension.find
import click.seichi.gigantic.extension.setDisplayName
import click.seichi.gigantic.extension.setLore
import click.seichi.gigantic.extension.wrappedLocale
import click.seichi.gigantic.message.messages.HookedItemMessages
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * @author tar0ss
 */
object FixedButtons {

    val PICKEL = object : Button {
        override fun getItemStack(player: Player): ItemStack? {
            return ItemStack(Material.DIAMOND_PICKAXE).apply {
                setDisplayName(HookedItemMessages.PICKEL.asSafety(player.wrappedLocale))
                setLore(*HookedItemMessages.PICKEL_LORE(player)
                        .map { it.asSafety(player.wrappedLocale) }
                        .toTypedArray()
                )
                itemMeta = itemMeta.apply {
                    isUnbreakable = true
                    addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                    addItemFlags(ItemFlag.HIDE_ENCHANTS)
                    addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
                }
                val mineBurst = player.find(CatalogPlayerCache.MINE_BURST) ?: return@apply
                if (mineBurst.duringFire()) {
                    addEnchantment(Enchantment.DIG_SPEED, 5)
                }
            }
        }

        override fun onClick(player: Player, event: InventoryClickEvent) {
        }
    }

    val SHOVEL = object : Button {
        override fun getItemStack(player: Player): ItemStack? {
            return ItemStack(Material.DIAMOND_SHOVEL).apply {
                setDisplayName(HookedItemMessages.SHOVEL.asSafety(player.wrappedLocale))
                setLore(*HookedItemMessages.SHOVEL_LORE(player)
                        .map { it.asSafety(player.wrappedLocale) }
                        .toTypedArray()
                )
                itemMeta = itemMeta.apply {
                    isUnbreakable = true
                    addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                    addItemFlags(ItemFlag.HIDE_ENCHANTS)
                    addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
                }
                val mineBurst = player.find(CatalogPlayerCache.MINE_BURST) ?: return@apply
                if (mineBurst.duringFire()) {
                    addEnchantment(Enchantment.DIG_SPEED, 5)
                }

            }
        }

        override fun onClick(player: Player, event: InventoryClickEvent) {
        }
    }

    val AXE = object : Button {
        override fun getItemStack(player: Player): ItemStack? {
            return ItemStack(Material.DIAMOND_AXE).apply {
                setDisplayName(HookedItemMessages.AXE.asSafety(player.wrappedLocale))
                setLore(*HookedItemMessages.AXE_LORE(player)
                        .map { it.asSafety(player.wrappedLocale) }
                        .toTypedArray()
                )
                itemMeta = itemMeta.apply {
                    isUnbreakable = true
                    addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                    addItemFlags(ItemFlag.HIDE_ENCHANTS)
                    addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
                }
                val mineBurst = player.find(CatalogPlayerCache.MINE_BURST) ?: return@apply
                if (mineBurst.duringFire()) {
                    addEnchantment(Enchantment.DIG_SPEED, 5)
                }
            }
        }

        override fun onClick(player: Player, event: InventoryClickEvent) {
        }
    }


    val BUCKET = object : Button {
        override fun getItemStack(player: Player): ItemStack? {
            return ItemStack(Material.BUCKET).apply {
                setDisplayName(HookedItemMessages.BUCKET.asSafety(player.wrappedLocale))
                setLore(*HookedItemMessages.BUCKET_LORE(player)
                        .map { it.asSafety(player.wrappedLocale) }
                        .toTypedArray()
                )
                itemMeta = itemMeta.apply {
                    isUnbreakable = true
                    addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                    addItemFlags(ItemFlag.HIDE_ENCHANTS)
                    addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
                }
            }
        }

        override fun onClick(player: Player, event: InventoryClickEvent) {
        }
    }


}