package me.adelemphii.skyburgerchallenges.managers;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.listeners.events.SkyburgerExperienceChangeEvent;
import me.adelemphii.skyburgerchallenges.utility.PlayerExperienceUtility;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ExperienceManager {

    private final SkyburgerChallenges plugin;
    private final WorldBorderManager worldBorderManager;
    private final BossBarManager bossBarManager;

    private int requiredExperienceCapLevel = 15;
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

    public void setLevels(int levels) {
        this.levels = levels;
        Bukkit.getPluginManager().callEvent(new SkyburgerExperienceChangeEvent());
    }

    public int getLevels() {
        return levels;
    }

    public void addLevels(int levels) {
        this.levels += levels;
        Bukkit.getPluginManager().callEvent(new SkyburgerExperienceChangeEvent());
    }

    public void removeLevels(int levels) {
        this.levels -= levels;
        Bukkit.getPluginManager().callEvent(new SkyburgerExperienceChangeEvent());
    }

    public int getRequiredExperienceCap() {
        return PlayerExperienceUtility.getXPRequiredForNextLevel(requiredExperienceCapLevel - 1);
    }

    public int getRequiredExperienceCapLevel() {
        return requiredExperienceCapLevel;
    }

    public void setRequiredExperienceCapLevel(int requiredExperienceCapLevel) {
        this.requiredExperienceCapLevel = requiredExperienceCapLevel;
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
        this.requiredExperienceCapLevel = plugin.getConfig().getInt("required-experience-cap", 15);

    }

    public void saveExperience() {
        plugin.getConfig().set("total-levels", this.getLevels());
        plugin.getConfig().set("required-experience-cap", this.getRequiredExperienceCapLevel());
        plugin.saveConfig();
    }
}