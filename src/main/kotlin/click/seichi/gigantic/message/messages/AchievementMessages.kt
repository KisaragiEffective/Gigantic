package click.seichi.gigantic.message.messages

import click.seichi.gigantic.config.Config
import click.seichi.gigantic.message.ChatMessage
import click.seichi.gigantic.message.ChatMessageProtocol
import click.seichi.gigantic.message.LocalizedText
import click.seichi.gigantic.quest.Quest
import click.seichi.gigantic.will.Will
import org.bukkit.ChatColor
import java.math.RoundingMode
import java.util.*

/**
 * @author tar0ss
 */
object AchievementMessages {

    val FIRST_JOIN = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE to "${ChatColor.AQUA}" +
                    "ブロックを壊そう!!\n" +
                    "${ChatColor.GRAY}" +
                    "メニューからランダムテレポートができるぞ!!\n"
    ))

    val FIRST_LEVEL_UP = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.YELLOW}" +
                        "ツールは破壊するブロックによって自動で切り替えられる!!\n" +
                        "手動で切り替えたいときは\"f\" キー を押してみよう!!"
            }
    ))

    val UNLOCK_SKILL_MINE_COMBO = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.YELLOW}" +
                        "バフスキル: マインコンボ解禁!!\n" +
                        "${ChatColor.GRAY}" +
                        "パッシブ効果: ブロックを連続破壊してコンボが切れないようにしよう！\n" +
                        "コンボ数によって採掘速度が上昇するぞ！\n"
            }
    ))


    val UNLOCK_SKILL_MINE_BURST = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.YELLOW}" +
                        "バフスキル: マインバースト を覚えた!!\n" +
                        "${ChatColor.GRAY}" +
                        "アクティブ効果: 少しの間だけ掘る速度が上昇!!\n"
            }
    ))

    val UNLOCK_FLASH = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.YELLOW}" +
                        "移動スキル: フラッシュ を覚えた!!\n" +
                        "${ChatColor.GRAY}" +
                        "アクティブ効果: ブロックに向けて発動するとそのブロックの上にワープ!!\n"
            }
    ))

    val MANA_STONE = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.AQUA}" +
                        "マナストーンを見つけた!!\n" +
                        "${ChatColor.GRAY}" +
                        "マナストーンを持った状態で魔法が使えるようになった!!\n" +
                        "右クリックでマナストーンを持ってみよう!!"
            }
    ))

    val UNLOCK_SPELL_STELLA_CLAIR = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.YELLOW}" +
                        "回復魔法: ステラ・クレア を覚えた!!\n" +
                        "${ChatColor.GRAY}" +
                        "パッシブ効果: ブロックを破壊で最大マナの${Config.SKILL_HEAL_RATIO.toBigDecimal().setScale(1, RoundingMode.HALF_UP)}% を回復!!\n" +
                        "通常破壊時に一定確率で発動する\n"
            }
    ))

    val UNLOCK_SPELL_APOSTOLUS = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.YELLOW}" +
                        "破壊魔法: アポストル を覚えた!!\n" +
                        "${ChatColor.GRAY}" +
                        "アクティブ効果: ブロック破壊時に，周囲のブロックも同時に破壊する!!\n" +
                        "通常破壊時に発動する\n"
            }
    ))

    val TELEPORT_PLAYER = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.YELLOW}" +
                        "プレイヤーテレポートが使えるようになった!!\n" +
                        "${ChatColor.GRAY}" +
                        "メニューからテレポート先を選択!!\n"
            }
    ))

    val TELEPORT_HOME = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.YELLOW}" +
                        "好きな場所をホームとしてテレポート先として登録できるようになった!!\n" +
                        "${ChatColor.GRAY}" +
                        "メニューから選択!!\n"
            }
    ))

    val TELEPORT_LAST_DEATH = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.YELLOW}" +
                        "ラストデステレポートが使えるようになった!!\n" +
                        "${ChatColor.GRAY}" +
                        "最後に死亡した場所に戻ることができる!!\n" +
                        "メニューから選択!!\n"
            }
    ))

    val QUEST_ORDER = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${Quest.COLOR}" +
                        "新しいクエストが届いた\n"
            }
    ))

    val QUEST_ORDER_FIRST = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${Quest.COLOR}" +
                        "初めてのクエストが届いた\n" +
                        "メニューからクエストを受注しよう!!"
            }
    ))

    val JUMP = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.YELLOW}" +
                        "大きくジャンプできるようになった!!\n" +
                        "${ChatColor.GRAY}" +
                        "ジャンプアイテムを持ってスニークしてみよう!!\n"
            }
    ))

    val SWORD = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.YELLOW}" +
                        "剣を扱えるようになった!!"
            }
    ))

    val WILL = { will: Will ->
        ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
                Locale.JAPANESE.let {
                    it to "" + will.chatColor +
                            "${ChatColor.BOLD}" +
                            will.getName(it) +
                            "の意志 " +
                            "${ChatColor.RESET}${ChatColor.YELLOW}" +
                            "と交感できるようになった!!"
                }
        ))
    }

    val FIRST_WILL = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.GRAY}" +
                        "ブロックを破壊すると，稀に意志が出現する!!\n" +
                        "意志が出現したら近付いてエーテルを獲得してみよう!!\n" +
                        "エーテルを100個集めたら，メニューにあるレリック生成器を使って\nレリックを獲得できるぞ!!"
            }
    ))

    val FIRST_RELIC = ChatMessage(ChatMessageProtocol.CHAT, LocalizedText(
            Locale.JAPANESE.let {
                it to "${ChatColor.GRAY}" +
                        "メニューから手に入れたレリックを確認しよう!!"
            }
    ))

}