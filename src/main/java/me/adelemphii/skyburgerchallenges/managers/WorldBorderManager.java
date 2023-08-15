package me.adelemphii.skyburgerchallenges.managers;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
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

        WorldBorder worldBorder = world.getWorldBorder();
        long timeInSeconds = calculateTimeInSeconds(worldBorder.getSize(), newSize);
        worldBorder.setSize(newSize, TimeUnit.SECONDS, timeInSeconds);
    }

    private double calculateNewBorderSize(int levels) {
        return Math.max(5.5, 5.5 + levels);
    }

    private long calculateTimeInSeconds(double currentSize, double newSize) {
        double distance = Math.abs(newSize - currentSize);

        if (distance <= 7) {
            // For distances up to 7 blocks, use a constant speed of 1 block per second
            return (long) distance;
        } else {
            // For distances larger than 7 blocks, adjust the speed as needed (e.g., 7 blocks per second)
            return (long) (distance / 10);
        }
    }
}
