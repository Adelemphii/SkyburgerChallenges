package me.adelemphii.skyburgerchallenges.managers;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import java.util.concurrent.TimeUnit;

public class WorldBorderManager {

    private final ExperienceManager experienceManager;
    private final SkyburgerChallenges plugin;

    public WorldBorderManager(SkyburgerChallenges plugin, ExperienceManager experienceManager) {
        this.plugin = plugin;
        this.experienceManager = experienceManager;
    }

    public void updateWorldBorders() {
        int xpAmount = experienceManager.getLevels();

        for (World world : plugin.getServer().getWorlds()) {
            updateWorldBorder(world, xpAmount);
        }
    }

    private void updateWorldBorder(World world, int levels) {
        double newSize = calculateNewBorderSize(levels);
        Bukkit.broadcast(Component.text(newSize));

        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setSize(newSize, TimeUnit.SECONDS, 10);
    }

    private double calculateNewBorderSize(int levels) {
        return Math.min(5.5, 5.5 + levels);
    }
}
