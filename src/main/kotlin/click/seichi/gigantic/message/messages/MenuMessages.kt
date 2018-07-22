package click.seichi.gigantic.message.messages

import click.seichi.gigantic.message.LocalizedText
import click.seichi.gigantic.raid.RaidBattle
import org.bukkit.ChatColor
import java.math.RoundingMode
import java.util.*

/**
 * @author tar0ss
 */
object MenuMessages {

    val LINE = (1..23).joinToString("") { "-" }

    val PROFILE = LocalizedText(
            Locale.JAPANESE to "${ChatColor.AQUA}${ChatColor.BOLD}${ChatColor.UNDERLINE}プロフィール"
    )

    val RAID_BOSS = LocalizedText(
            Locale.JAPANESE to "${ChatColor.DARK_RED}${ChatColor.BOLD}${ChatColor.UNDERLINE}レイドボス"
    )

    val REST = LocalizedText(
            Locale.JAPANESE to "${ChatColor.GREEN}${ChatColor.BOLD}${ChatColor.UNDERLINE}休憩"
    )

    val BACK_FROM_REST = LocalizedText(
            Locale.JAPANESE to "${ChatColor.YELLOW}${ChatColor.BOLD}${ChatColor.UNDERLINE}戻る"
    )

    val BACK_BUTTON = { menuTitle: String ->
        LocalizedText(
                Locale.JAPANESE to "$menuTitle${ChatColor.RESET}${ChatColor.WHITE}に戻る"
        )
    }

    val BATTLE_BUTTON_TITLE = { bossName: String ->
        LocalizedText(
                Locale.JAPANESE to "${ChatColor.RED}$bossName"
        )
    }

    val BATTLE_BUTTON_LORE = { raidBattle: RaidBattle ->
        val bossDrop = raidBattle.boss.dropRelicSet
        mutableListOf(
                LocalizedText(
                        Locale.JAPANESE to "${ChatColor.GRAY}残りHP : ${raidBattle.raidBoss.health.toBigDecimal().setScale(1, RoundingMode.UP)}"
                ),
                LocalizedText(
                        Locale.JAPANESE to "${ChatColor.GRAY}戦闘中 : ${raidBattle.joinedPlayerSet.size}人"
                )
                // TODO drop process
//                LocalizedText(
//                        Locale.JAPANESE to "${ChatColor.GRAY}落とすもの : "
//                )
        ).apply {
            //            bossDrop.forEach { drop ->
//                val color = when (drop.relic.rarity) {
//                    RelicRarity.NORMAL -> ChatColor.GRAY
//                    RelicRarity.RARE -> ChatColor.DARK_PURPLE
//                }
//                add(
//                        LocalizedText(
//                                Locale.JAPANESE.let { locale ->
//                                    locale to "$color${drop.relic.localizedName.asSafety(locale)}"
//                                }
//                        )
//                )
//            }
        }
    }

    val BATTLE_BUTTON_JOIN = LocalizedText(
            Locale.JAPANESE to "${ChatColor.WHITE}${ChatColor.BOLD}${ChatColor.UNDERLINE}クリックで参加"
    )

    val BATTLE_BUTTON_LEFT = LocalizedText(
            Locale.JAPANESE to "${ChatColor.YELLOW}${ChatColor.BOLD}${ChatColor.UNDERLINE}クリックで離脱"
    )

    val BATTLE_BUTTON_DROPPED = LocalizedText(
            Locale.JAPANESE to "${ChatColor.RED}復帰不可"
    )

    val BATTLE_BUTTON_JOINED = LocalizedText(
            Locale.JAPANESE to "${ChatColor.RED}他の戦闘に参加中"
    )

}