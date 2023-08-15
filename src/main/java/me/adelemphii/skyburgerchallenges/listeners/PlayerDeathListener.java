package me.adelemphii.skyburgerchallenges.listeners;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private final SkyburgerChallenges plugin;

    public PlayerDeathListener(SkyburgerChallenges plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        int lostExp = player.getTotalExperience();
        int lostLevels = player.getLevel();

        player.setExp(0);
        player.setTotalExperience(0);
        player.setLevel(0);
    }
}
