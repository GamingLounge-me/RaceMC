package me.gaminglounge.racemc.Listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import me.gaminglounge.racemc.RaceMC;

import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.Bukkit;

public class PlayerJoinLogic implements Listener{
    

    public PlayerJoinLogic(RaceMC raceMC) {
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.teleport(new Location(Bukkit.getWorld("lobby"), 0, 100, 0));
    }
}
