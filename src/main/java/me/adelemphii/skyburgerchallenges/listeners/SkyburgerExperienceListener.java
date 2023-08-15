package me.adelemphii.skyburgerchallenges.listeners;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.listeners.events.SkyburgerExperienceChangeEvent;
import me.adelemphii.skyburgerchallenges.managers.BossBarManager;
import me.adelemphii.skyburgerchallenges.managers.ExperienceManager;
import me.adelemphii.skyburgerchallenges.managers.WorldBorderManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkyburgerExperienceListener implements Listener {

    private final WorldBorderManager worldBorderManager;
    private final BossBarManager bossBarManager;

    public SkyburgerExperienceListener(SkyburgerChallenges plugin) {

        ExperienceManager experienceManager = plugin.getExperienceManager();
        this.worldBorderManager = experienceManager.getWorldBorderManager();
        this.bossBarManager = experienceManager.getBossBarManager();
    }

    @EventHandler
    public void onSkyburgerExperienceChange(SkyburgerExperienceChangeEvent event) {
        this.worldBorderManager.updateWorldBorders();
        this.bossBarManager.updateBossBar();
    }
}
