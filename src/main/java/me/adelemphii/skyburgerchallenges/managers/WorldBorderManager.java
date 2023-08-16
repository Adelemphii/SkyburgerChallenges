package me.adelemphii.skyburgerchallenges.managers;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

public class WorldBorderManager {

    private final ExperienceManager experienceManager;
    private final SkyburgerChallenges plugin;

    private Location centerLocation;

    public WorldBorderManager(SkyburgerChallenges plugin, ExperienceManager experienceManager) {
        this.plugin = plugin;
        this.experienceManager = experienceManager;
    }

    public void updateWorldBorders() {
        int xpAmount = experienceManager.getLevels();

        for (World world : plugin.getServer().getWorlds()) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                updateWorldBorder(player, xpAmount);
            }
        }
    }

    public WorldBorder updateWorldBorder(Player player, int levels) {
        double newSize = calculateNewBorderSize(levels);
        WorldBorder worldBorder = player.getWorldBorder() != null ? player.getWorldBorder() : Bukkit.createWorldBorder();

        worldBorder.setCenter(-8, -7);
        worldBorder.setSize(newSize);

        player.setWorldBorder(worldBorder);
        return worldBorder;
    }

    private double calculateNewBorderSize(int levels) {
        return Math.max(6, 6 + (levels));
    }

    public void setCenterLocation(Location location) {
        this.centerLocation = location;
    }

    public Location getCenterLocation() {
        return centerLocation;
    }

    public void saveCenterLocation() {
        plugin.getConfig().set("center-location", centerLocation);
        plugin.saveConfig();
    }

    public void loadLocation() {
        this.centerLocation = plugin.getConfig().getLocation("center-location",
                new Location(Bukkit.getWorld("world"), 0, 0, 0));
    }
}
