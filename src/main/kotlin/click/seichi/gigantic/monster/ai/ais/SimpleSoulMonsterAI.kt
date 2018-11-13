package click.seichi.gigantic.monster.ai.ais

import click.seichi.gigantic.cache.key.Keys
import click.seichi.gigantic.extension.centralLocation
import click.seichi.gigantic.extension.getOrPut
import click.seichi.gigantic.extension.isCrust
import click.seichi.gigantic.extension.isSurface
import click.seichi.gigantic.monster.ai.SoulMonsterAI
import click.seichi.gigantic.util.Random
import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player

/**
 * @author tar0ss
 */
class SimpleSoulMonsterAI : SoulMonsterAI {

    override fun searchNextTargetLocation(chunk: Chunk, target: Player, currentLocation: Location): Location {
        var count = 0
        var targetLocation: Location
        do {
            targetLocation = chunk.getBlock(Random.nextInt(15), 0, Random.nextInt(15)).let { block ->
                chunk.world.getHighestBlockAt(block.location)
                        .getRelative(BlockFace.UP, 5)
                        .centralLocation
                        .add(0.0, -0.5, 0.0)
            }
            val distance = targetLocation.distance(currentLocation)
        } while ((distance < 5 || 12 <= distance) && count++ < 10)
        return targetLocation
    }

    override fun getAttackBlockSet(chunk: Chunk, target: Player, attackTimes: Int): Set<Block> {
        val blocks = mutableSetOf<Block>()
        (-3..3).forEach { x ->
            (-3..3).forEach { z ->
                (-1..5).forEach { y ->
                    blocks.add(target.location.block.getRelative(x, y, z))
                }
            }
        }
        val selectedLocations = target.getOrPut(Keys.ATTACK_WAIT_LOCATION_SET)
        return blocks.filter { block ->
            block.isCrust && block.isSurface && block.chunk == chunk && !selectedLocations.contains(block.location)
        }.sortedByDescending { it.y + Random.nextDouble() }.take(attackTimes).toSet()
    }

}