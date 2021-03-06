package click.seichi.gigantic.ranking

import click.seichi.gigantic.cache.cache.PlayerCache
import click.seichi.gigantic.cache.cache.RankingPlayerCache
import click.seichi.gigantic.cache.key.Keys
import click.seichi.gigantic.database.RankingEntity
import click.seichi.gigantic.database.table.ranking.RankingScoreTable
import click.seichi.gigantic.extension.potionOf
import click.seichi.gigantic.message.LocalizedText
import click.seichi.gigantic.player.ExpReason
import click.seichi.gigantic.relic.Relic
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SortOrder
import java.util.*

/**
 * @author tar0ss
 */
enum class Score(
        val column: Column<Long>,
        val sortOrder: SortOrder,
        private val icon: ItemStack,
        private val localizedName: LocalizedText,
        private val localizedUnit: LocalizedText
) {
    EXP(
            RankingScoreTable.exp,
            SortOrder.DESC,
            ItemStack(Material.EXPERIENCE_BOTTLE),
            LocalizedText(
                    Locale.JAPANESE to "累計獲得経験値ランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "exp"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            var exp = 0.toBigDecimal()
            Keys.EXP_MAP.values.forEach { exp += cache.getOrDefault(it) }
            entity.score.exp = exp.toLong()
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.RANK_EXP.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.exp
        }

    },
    BREAK_BLOCK(
            RankingScoreTable.breakBlock,
            SortOrder.DESC,
            potionOf(Color.GREEN),
            LocalizedText(
                    Locale.JAPANESE to "累計通常破壊量ランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "block"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            val value = cache.getOrDefault(Keys.EXP_MAP.getValue(ExpReason.MINE_BLOCK)).toLong()
            entity.score.breakBlock = value
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.RANK_BREAK_BLOCK.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.breakBlock
        }
    },
    MULTI_BREAK_BLOCK(
            RankingScoreTable.multiBreakBlock,
            SortOrder.DESC,
            potionOf(Color.BLUE),
            LocalizedText(
                    Locale.JAPANESE to "累計範囲破壊量ランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "block"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            val value = cache.getOrDefault(Keys.EXP_MAP.getValue(ExpReason.SPELL_MULTI_BREAK)).toLong()
            entity.score.multiBreakBlock = value
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.RANK_MULTI_BREAK_BLOCK.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.multiBreakBlock
        }
    },
    RELIC_BONUS(
            RankingScoreTable.relicBonus,
            SortOrder.DESC,
            potionOf(Color.PURPLE),
            LocalizedText(
                    Locale.JAPANESE to "累計レリックボーナスランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "exp"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            val value = cache.getOrDefault(Keys.EXP_MAP.getValue(ExpReason.RELIC_BONUS)).toLong()
            entity.score.relicBonus = value
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.RANK_RELIC_BONUS.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.relicBonus
        }
    },
    MAX_COMBO(
            RankingScoreTable.maxCombo,
            SortOrder.DESC,
            potionOf(Color.RED),
            LocalizedText(
                    Locale.JAPANESE to "最大コンボ数ランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "combo"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            val value = cache.getOrDefault(Keys.MAX_COMBO).toLong()
            entity.score.maxCombo = value
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.RANK_MAX_COMBO.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.maxCombo
        }
    },
    RELIC(
            RankingScoreTable.relic,
            SortOrder.DESC,
            potionOf(Color.LIME),
            LocalizedText(
                    Locale.JAPANESE to "累計レリック数ランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "個"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            var value = 0L
            Relic.values().forEach { value += cache.getOrDefault(Keys.RELIC_MAP.getValue(it)) }
            entity.score.relic = value
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.RANK_RELIC.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.relic
        }
    },
    STRIP_MINE(
            RankingScoreTable.stripMine,
            SortOrder.DESC,
            potionOf(Color.BLACK),
            LocalizedText(
                    Locale.JAPANESE to "露天掘り面積ランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "chunk"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            val value = cache.getOrDefault(Keys.STRIP_MINE)
            val chunk = value.div(16.0).div(16.0).toLong()
            entity.score.stripMine = chunk
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.RANK_STRIP_MINE.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.stripMine
        }
    },
    ;

    fun getIcon() = icon.clone()
    fun getName(locale: Locale) = localizedName.asSafety(locale)
    fun getUnit(locale: Locale) = localizedUnit.asSafety(locale)

    abstract fun write(entity: RankingEntity, cache: PlayerCache)

    abstract fun read(entity: RankingEntity, cache: RankingPlayerCache)

    abstract fun getValue(rankingPlayer: RankingPlayer): Long
}