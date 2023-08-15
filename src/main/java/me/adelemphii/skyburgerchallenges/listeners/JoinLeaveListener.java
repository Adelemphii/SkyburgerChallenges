package me.adelemphii.skyburgerchallenges.listeners;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

    private final SkyburgerChallenges plugin;

    public JoinLeaveListener(SkyburgerChallenges plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getExperienceManager().getBossBarManager().addPlayerToBossBar(event.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        plugin.getExperienceManager().getBossBarManager().removePlayerFromBossBar(event.getPlayer());
    }
}
