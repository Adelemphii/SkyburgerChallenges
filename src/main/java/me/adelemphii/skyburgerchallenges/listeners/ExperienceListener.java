package me.adelemphii.skyburgerchallenges.listeners;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.managers.ExperienceManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class ExperienceListener implements Listener {

    private final ExperienceManager experienceManager;

    public ExperienceListener(SkyburgerChallenges plugin) {
        this.experienceManager = plugin.getExperienceManager();
    }

    @EventHandler
    public void onPlayerLevelChange(PlayerLevelChangeEvent event) {
        experienceManager.setLevels(event.getPlayer().getLevel());
        experienceManager.setTotalExperiencePoints(event.getPlayer().getTotalExperience());

        Bukkit.getOnlinePlayers().forEach(all -> all.setLevel(experienceManager.getLevels()));
    }

    @EventHandler
    public void onPlayerExperienceChange(PlayerExpChangeEvent event) {
        experienceManager.addExperience(event.getAmount());

        Bukkit.getOnlinePlayers().forEach(all -> all.setExp(event.getPlayer().getExp()));
    }
}
