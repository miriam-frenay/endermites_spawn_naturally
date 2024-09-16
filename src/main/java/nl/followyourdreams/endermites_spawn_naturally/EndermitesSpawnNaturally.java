package nl.followyourdreams.endermites_spawn_naturally;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class EndermitesSpawnNaturally extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new SpawnEndermiteOnBreakingEndstone(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
