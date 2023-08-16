package me.adelemphii.skyburgerchallenges.managers;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class WorldBorderManager {

    private final ExperienceManager experienceManager;
    private final SkyburgerChallenges plugin;

    private Vector overworldCenter;
    private Vector netherCenter;

    public WorldBorderManager(SkyburgerChallenges plugin, ExperienceManager experienceManager) {
        this.plugin = plugin;
        this.experienceManager = experienceManager;
    }

    public void updateWorldBorders() {
        int levels = experienceManager.getLevels();

        for(Player player : Bukkit.getOnlinePlayers()) {
            updateWorldBorder(player, levels);
        }
    }

    public WorldBorder updateWorldBorder(Player player, int levels) {
        if(player.getWorld().getEnvironment() == World.Environment.NETHER) {
            return updateNetherBorder(player, levels);
        } else if(player.getWorld().getEnvironment() == World.Environment.NORMAL) {
            return updateOverworldBorder(player, levels);
        }
        return null;
    }

    public WorldBorder updateOverworldBorder(Player player, int levels) {
        double newSize = calculateNewBorderSize(levels);
        WorldBorder worldBorder = player.getWorldBorder() != null ? player.getWorldBorder() : Bukkit.createWorldBorder();

        // (-8, -7)
        worldBorder.setCenter(overworldCenter.getBlockX(), overworldCenter.getBlockZ());
        worldBorder.setSize(newSize);

        player.setWorldBorder(worldBorder);
        return worldBorder;
    }

    public WorldBorder updateNetherBorder(Player player, int levels) {
        double newSize = calculateNewBorderSize(levels);
        WorldBorder worldBorder = player.getWorldBorder() != null ? player.getWorldBorder() : Bukkit.createWorldBorder();

        worldBorder.setCenter(netherCenter.getBlockX(), netherCenter.getBlockZ());
        worldBorder.setSize(newSize);

        player.setWorldBorder(worldBorder);
        return worldBorder;
    }

    private double calculateNewBorderSize(int levels) {
        return Math.max(6, 6 + (levels));
    }

    public void setOverworldCenter(Vector overworldCenter) {
        this.overworldCenter = overworldCenter;
    }

    public void setNetherCenter(Vector netherCenter) {
        this.netherCenter = netherCenter;
    }

    public Vector getOverworldCenter() {
        return overworldCenter;
    }

    public Vector getNetherCenter() {
        return netherCenter;
    }

    public void saveLocations() {
        plugin.getConfig().set("overworld-center", overworldCenter);
        plugin.getConfig().set("nether-center", netherCenter);
        plugin.saveConfig();
    }

    public void loadLocations() {
        this.overworldCenter = plugin.getConfig().getVector("overworld-center", new Vector(0, 0, 0));
        this.netherCenter = plugin.getConfig().getVector("nether-center", new Vector(0, 0, 0));
    }
}
