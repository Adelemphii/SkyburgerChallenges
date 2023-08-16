package me.adelemphii.skyburgerchallenges.listeners;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.listeners.events.SkyburgerHealthChangeEvent;
import me.adelemphii.skyburgerchallenges.objects.effects.JingleSound;
import me.adelemphii.skyburgerchallenges.objects.effects.PentagramEffect;
import me.adelemphii.skyburgerchallenges.utility.EffectUtility;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
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

            if(event.getNewHealth() < event.getOldHealth()) {
                playOminous(player);
            } else {
                playLucky(player);
            }
        });
    }

    private void playOminous(Player player) {
        double radius = 1.5; // Radius of the pentagram effect
        PentagramEffect ominousEffect = new PentagramEffect(player, radius);
        ominousEffect.runTaskTimer(plugin, 0L, 1L);

        player.playSound(Sound.sound(Key.key("item.goat_horn.sound.6"), Sound.Source.AMBIENT, 1f, 1f));
    }

    public void playLucky(Player player) {
        JingleSound jingleSound = new JingleSound(EffectUtility.HAPPY_JINGLE, Instrument.BIT, player);
        jingleSound.runTaskTimer(plugin, 0L, 2L);
    }
}
