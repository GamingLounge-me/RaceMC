package me.gaminglounge.racemc; 
 
import java.io.File;
import java.util.logging.Level;


import org.bukkit.plugin.PluginManager; 
import org.bukkit.plugin.java.JavaPlugin;

import com.infernalsuite.asp.api.loaders.SlimeLoader;
import com.infernalsuite.asp.loaders.file.FileLoader;

import me.gaminglounge.racemc.Listeners.PlayerJoinLogic; 
 
public final class RaceMC extends JavaPlugin { 
 
    public static RaceMC INSTANCE; 
    public SlimeLoader loader; 
    public WorldManager worldManager; 

    @Override
    public void onLoad() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        
        File slimeWorldsFolder = new File(getDataFolder(), "slime_worlds");
        getLogger().info("Slime worlds folder path: " + slimeWorldsFolder.getAbsolutePath());
        if (!slimeWorldsFolder.exists()) {
            if (!slimeWorldsFolder.mkdirs()) {
                getLogger().severe("Failed to create slime_worlds folder!");
                // Optionally disable the plugin if the folder cannot be created.
                getServer().getPluginManager().disablePlugin(this);
                return;
            }
        }
        
        try {
            loader = new FileLoader(slimeWorldsFolder.getAbsoluteFile());
        } catch (IllegalStateException e) {
            this.getLogger().log(Level.WARNING, e.toString());
        }

        worldManager = new WorldManager();
        
        // load Lobby at this stage to prevent any issues
        worldManager.loadWorld("lobby");
        listener(); // Register the listener
    }

    public SlimeLoader getLoader() {
        return loader;
    }

    @Override
    public void onDisable() {
        
    }

    public void listener() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinLogic(this), this); // Pass RaceMC instance
    } 
}
