package me.adelemphii.skyburgerchallenges.listeners;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.managers.ExperienceManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerWorldChangeListener implements Listener {

    private final ExperienceManager experienceManager;

    public PlayerWorldChangeListener(SkyburgerChallenges plugin) {
        this.experienceManager = plugin.getExperienceManager();
    }

    @EventHandler
    public void onPlayerWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        experienceManager.getWorldBorderManager().updateWorldBorder(player, experienceManager.getLevels());
    }
}
