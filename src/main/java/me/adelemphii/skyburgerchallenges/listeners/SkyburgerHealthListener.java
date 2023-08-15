package me.adelemphii.skyburgerchallenges.listeners;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.listeners.events.SkyburgerHealthChangeEvent;
import me.adelemphii.skyburgerchallenges.objects.effects.PentagramEffect;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkyburgerHealthListener implements Listener {

    private final SkyburgerChallenges plugin;

    public SkyburgerHealthListener(SkyburgerChallenges plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSkyburgerHealthChange(SkyburgerHealthChangeEvent event) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(event.getNewHealth());

            double radius = 1.5; // Radius of the pentagram effect
            PentagramEffect ominousEffect = new PentagramEffect(player, radius);
            ominousEffect.runTaskTimer(plugin, 0L, 1L);
        });
    }
}
