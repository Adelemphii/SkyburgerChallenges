package me.adelemphii.skyburgerchallenges.managers;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.listeners.events.SkyburgerExperienceChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ExperienceManager {

    private final SkyburgerChallenges plugin;
    private final WorldBorderManager worldBorderManager;
    private final BossBarManager bossBarManager;

    private int totalExperiencePoints = 0;
    private int levels = 0;

    public ExperienceManager(SkyburgerChallenges plugin) {
        this.plugin = plugin;

        this.worldBorderManager = new WorldBorderManager(plugin, this);
        this.bossBarManager = new BossBarManager(plugin, this);

        new BukkitRunnable() {
            @Override
            public void run() {
                saveExperience();
            }                                             // 1 hour
        }.runTaskTimerAsynchronously(plugin, 0, 72000L);
    }

    public void setTotalExperiencePoints(int totalExperiencePoints) {
        this.totalExperiencePoints = totalExperiencePoints;
        Bukkit.getPluginManager().callEvent(new SkyburgerExperienceChangeEvent());
    }

    public void setLevels(int levels) {
        this.levels = levels;
        Bukkit.getPluginManager().callEvent(new SkyburgerExperienceChangeEvent());
    }

    public int getTotalExperiencePoints() {
        return totalExperiencePoints;
    }

    public int getLevels() {
        return levels;
    }

    public void addExperience(int experiencePoints) {
        this.totalExperiencePoints += experiencePoints;
        Bukkit.getPluginManager().callEvent(new SkyburgerExperienceChangeEvent());
    }

    public void addLevels(int levels) {
        this.levels += levels;
        Bukkit.getPluginManager().callEvent(new SkyburgerExperienceChangeEvent());
    }

    public void removeExperience(int experiencePoints) {
        this.totalExperiencePoints -= experiencePoints;
        Bukkit.getPluginManager().callEvent(new SkyburgerExperienceChangeEvent());
    }

    public void removeLevels(int levels) {
        this.levels -= levels;
        Bukkit.getPluginManager().callEvent(new SkyburgerExperienceChangeEvent());
    }

    public BossBarManager getBossBarManager() {
        return bossBarManager;
    }

    public WorldBorderManager getWorldBorderManager() {
        return worldBorderManager;
    }

    // I know it's not great to do it like this, but I am not publicly releasing the plugin.
    // It's for a friend group and it's simple.
    public void loadExperience() {
        this.levels = plugin.getConfig().getInt("total-levels", 0);
        this.totalExperiencePoints = plugin.getConfig().getInt("total-experience", 0);
    }

    public void saveExperience() {
        plugin.getConfig().set("total-experience", this.getTotalExperiencePoints());
        plugin.getConfig().set("total-levels", this.getLevels());
        plugin.saveConfig();
    }
}