package me.adelemphii.skyburgerchallenges.listeners;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.managers.ExperienceManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class ExperienceListener implements Listener {

    private final ExperienceManager experienceManager;

    public ExperienceListener(SkyburgerChallenges plugin) {
        this.experienceManager = plugin.getExperienceManager();
    }

    @EventHandler
    public void onPlayerLevelChange(PlayerLevelChangeEvent event) {
        int levelDifference = event.getNewLevel() - event.getOldLevel();

        experienceManager.addLevels(levelDifference);
    }
}
