package click.seichi.gigantic.button.buttons.menu

import click.seichi.gigantic.button.Button
import click.seichi.gigantic.head.Head
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * @author tar0ss
 */
class PlayerHeadButton(private val uuid: UUID) : Button {

    override fun getItemStack(player: Player): ItemStack? {
        return getItemStack()
    }

    fun getItemStack(): ItemStack? {
        return Head.getOfflinePlayerHead(uuid)?.clone()
    }

    override fun onClick(player: Player, event: InventoryClickEvent) {
    }

}