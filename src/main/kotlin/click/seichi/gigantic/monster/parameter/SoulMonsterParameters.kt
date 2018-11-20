package click.seichi.gigantic.monster.parameter

import org.bukkit.Material

/**
 * @author tar0ss
 */
object SoulMonsterParameters {

    val PIG = SoulMonsterParameter(
            50L,
            10,
            0.1,
            1,
            Material.PINK_WOOL
    )

    val PIG_WARRIOR = SoulMonsterParameter(
            80L,
            20,
            0.15,
            2,
            Material.PINK_CONCRETE_POWDER
    )

    val MR_PIG = SoulMonsterParameter(
            100L,
            25,
            0.2,
            3,
            Material.PINK_CONCRETE
    )

    val BLAZE = SoulMonsterParameter(
            280L,
            40,
            0.12,
            2,
            Material.NETHERRACK
    )

    val BLAZE_WARRIOR = SoulMonsterParameter(
            400L,
            60,
            0.15,
            3,
            Material.NETHER_BRICKS
    )

    val BLUE_BLAZE = SoulMonsterParameter(
            700L,
            80,
            0.18,
            4,
            Material.BLUE_CONCRETE_POWDER
    )

    val CHICKEN = SoulMonsterParameter(
            70L,
            8,
            0.2,
            2,
            Material.WHITE_WOOL
    )

    val CHICKEN_KING = SoulMonsterParameter(
            140L,
            14,
            0.25,
            3,
            Material.WHITE_TERRACOTTA
    )

    val WITHER_SKELETON = SoulMonsterParameter(
            700L,
            100,
            0.3,
            3,
            Material.BLACK_CONCRETE_POWDER
    )

    val WITHER = SoulMonsterParameter(
            2500L,
            100,
            0.4,
            5,
            Material.BLACK_CONCRETE
    )

    val VILLAGER = SoulMonsterParameter(
            50L,
            0,
            0.1,
            0,
            Material.GRASS_BLOCK
    )

    val ZOMBIE_VILLAGER = SoulMonsterParameter(
            70L,
            0,
            0.1,
            1,
            Material.MOSSY_COBBLESTONE
    )

    val LADON = SoulMonsterParameter(
            50L,
            10,
            0.2,
            1,
            Material.GOLD_BLOCK
    )

    val UNDINE = SoulMonsterParameter(
            50L,
            10,
            0.2,
            1,
            Material.BLUE_GLAZED_TERRACOTTA
    )

    val SALAMANDRA = SoulMonsterParameter(
            50L,
            10,
            0.2,
            1,
            Material.RED_GLAZED_TERRACOTTA
    )

    val SYLPHID = SoulMonsterParameter(
            50L,
            10,
            0.2,
            1,
            Material.WHITE_GLAZED_TERRACOTTA
    )

    val NOMOS = SoulMonsterParameter(
            50L,
            10,
            0.2,
            1,
            Material.BROWN_GLAZED_TERRACOTTA
    )

    val LOA = SoulMonsterParameter(
            50L,
            10,
            0.2,
            1,
            Material.LIME_GLAZED_TERRACOTTA
    )

}