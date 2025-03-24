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
    SlimeWorld world;

    

    /**
     * Load a world from the loader folder.
     * @param worldname The name of the world to load.
     * @param worldPropertys Optional propertymap for the world to load, else gona return blank.
     */
    public void loadWorld(String worldname, SlimePropertyMap worldPropertys) {
        try {
            world = asp.readWorld(loader, worldname, false, worldPropertys);
            asp.loadWorld(world, false);
            Bukkit.getLogger().info("Loaded "+worldname.toString()+" successfully.");
        } catch (CorruptedWorldException | NewerFormatException | IOException e) {
            e.printStackTrace();
        }
        catch (UnknownWorldException e) {
            world = asp.createEmptyWorld(worldname, false, worldPropertys, loader);
            asp.loadWorld(world, false);
            Bukkit.getLogger().warning("The world "+worldname.toString()+"does not exist, creating a blank world.");
        }
    }
    public void loadWorld(String worldname){
        blankPropertys.setValue(SlimeProperties.WORLD_TYPE, "flat");
        loadWorld(worldname, blankPropertys);
    }


/**
 * Read a world from the loader folder.
 * @param worldname The name of the world to read.
 * @param worldProperty Optional propertymap for the world to read, else gona return blank.
 */
    public void readworld(String worldname, SlimePropertyMap worldProperty){
        try {
            world = asp.readWorld(loader, worldname, false, worldProperty);
            Bukkit.getLogger().info("Loaded "+worldname.toString()+" successfully.");
        } catch (CorruptedWorldException | NewerFormatException | IOException e) {
            e.printStackTrace();
        }
        catch (UnknownWorldException e) {
            world = asp.createEmptyWorld(worldname, false, worldProperty, loader);
            Bukkit.getLogger().warning("The world "+worldname.toString()+"does not exist, creating a blank world.");
        }
    }
    public void readworld(String worldname){
        blankPropertys.setValue(SlimeProperties.WORLD_TYPE, "flat");
        readworld(worldname, blankPropertys);
    }

    public void copyChunks(String fromWorld, String toWorld, Integer fromX, Integer fromZ, Integer toX, Integer toZ){

    }

}