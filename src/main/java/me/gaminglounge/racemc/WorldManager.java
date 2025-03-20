package me.gaminglounge.racemc;

import com.infernalsuite.asp.api.AdvancedSlimePaperAPI;
import com.infernalsuite.asp.api.exceptions.CorruptedWorldException;
import com.infernalsuite.asp.api.exceptions.NewerFormatException;
import com.infernalsuite.asp.api.exceptions.UnknownWorldException;
import com.infernalsuite.asp.api.loaders.SlimeLoader;
import com.infernalsuite.asp.api.world.SlimeWorld;
import com.infernalsuite.asp.api.world.properties.SlimeProperties;
import com.infernalsuite.asp.api.world.properties.SlimePropertyMap;

import java.io.IOException;

import org.bukkit.Bukkit;

public class WorldManager {
    
    private final AdvancedSlimePaperAPI asp = AdvancedSlimePaperAPI.instance();
    SlimeLoader loader = RaceMC.INSTANCE.getLoader();
    SlimePropertyMap blankPropertys = new SlimePropertyMap();
    


    public void loadWorld(String worldname) {
        blankPropertys.setValue(SlimeProperties.WORLD_TYPE, "flat");
        SlimeWorld world;
        try {
            world = asp.readWorld(loader, worldname, false, blankPropertys);
            asp.loadWorld(world, false);
            Bukkit.getLogger().info("Loaded "+worldname.toString()+" successfully.");
        } catch (CorruptedWorldException | NewerFormatException | IOException e) {
            e.printStackTrace();
        }
        catch (UnknownWorldException e) {
            world = asp.createEmptyWorld(worldname, false, blankPropertys, loader);
            asp.loadWorld(world, false);
            Bukkit.getLogger().warning("The world "+worldname.toString()+"does not exist, creating a blank world.");
        }
    }
}