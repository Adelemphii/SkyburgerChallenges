package me.adelemphii.skyburgerchallenges.listeners;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.attribute.Attribute;
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
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(plugin.getPunishmentManager().getMaxHealth());

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            WorldBorder worldBorder = plugin.getExperienceManager().getWorldBorderManager()
                    .updateWorldBorder(player, plugin.getExperienceManager().getLevels());

            if(!worldBorder.isInside(player.getLocation())) {
                player.teleport(worldBorder.getCenter().toHighestLocation().add(0, 1, 0));
            }
        }, 10L);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
    }
}
