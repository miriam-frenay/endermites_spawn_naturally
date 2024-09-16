package nl.followyourdreams.endermites_spawn_naturally;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

public class SpawnEndermiteOnBreakingEndstone implements Listener {
    Plugin plugin;

    public SpawnEndermiteOnBreakingEndstone(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        BlockState state = block.getState();
        World world = state.getWorld();
        if ((state.getType() == Material.END_STONE) && (world.getBiome(state.getLocation()) == Biome.END_HIGHLANDS)) {
            if (world.getBlockState(state.getX(), state.getY() - 1, state.getZ()).getType() == Material.END_STONE) {
                trySpawnEndermite(event, block, state, world);
            }
        }
    }

    private void trySpawnEndermite(BlockBreakEvent event, Block block, BlockState state, World world) {
        if (Math.random() < plugin.getConfig().getDouble("spawn-chance")) {
            block.setType(Material.AIR);
            Location location = new Location(world,
                    state.getX() + 0.25 + Math.random() * 0.5,
                    state.getY() + 0.25 + Math.random() * 0.5,
                    state.getZ() + 0.25 + Math.random() * 0.5,
                    (float) (Math.random() * 360.0),
                    0.0f
            );
            Endermite endermite = (Endermite) world.spawnEntity(location, EntityType.ENDERMITE);
            world.spawnParticle(Particle.REVERSE_PORTAL, location, 50);
            world.playSound(endermite, Sound.ENTITY_ENDERMITE_HURT, 1.0f, 1.0f);
            endermite.setTarget(event.getPlayer());
        }
    }
}
