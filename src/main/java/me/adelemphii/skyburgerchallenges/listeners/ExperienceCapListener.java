package me.adelemphii.skyburgerchallenges.listeners;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.utility.PlayerExperienceUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class ExperienceCapListener implements Listener {

    private final SkyburgerChallenges plugin;
    public ExperienceCapListener(SkyburgerChallenges plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerPointGainEvent(PlayerExpChangeEvent event) {
        Player player = event.getPlayer();
        int currentLevel = player.getLevel();
        if(currentLevel < plugin.getExperienceManager().getRequiredExperienceCapLevel()) {
            return;
        }
        int requiredExperienceCap = plugin.getExperienceManager().getRequiredExperienceCap();

        int xpRemaining = PlayerExperienceUtility.getXpLeftUntilNextLevel(player);
        int xpRequired = PlayerExperienceUtility.getXPRequiredForNextLevel(player.getLevel());

        int currentXP = xpRequired - xpRemaining;
        if(currentXP >= requiredExperienceCap) {
            player.setExp(0.0f);
            player.setLevel(player.getLevel() + 1);
        }
    }
}