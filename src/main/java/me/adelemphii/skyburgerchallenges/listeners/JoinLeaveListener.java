package me.adelemphii.skyburgerchallenges.listeners;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
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
        Player player = event.getPlayer();
        plugin.getExperienceManager().getBossBarManager().addPlayerToBossBar(player);

        WorldBorder worldBorder = player.getWorld().getWorldBorder();
        if(!worldBorder.isInside(player.getLocation())) {
            player.teleport(worldBorder.getCenter().toHighestLocation().add(0, 1, 0));
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        plugin.getExperienceManager().getBossBarManager().removePlayerFromBossBar(event.getPlayer());
    }
}
