package me.adelemphii.skyburgerchallenges.objects.effects;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;

public class ReviveEffect extends BukkitRunnable {

    private final Location location;
    private final Player player;

    private final SkyburgerChallenges plugin;

    public ReviveEffect(Player player, Location location, SkyburgerChallenges plugin) {
        this.player = player;
        this.location = location;

        this.plugin = plugin;
    }

    @Override
    public void run() {
        playReviveEffect();

        Duration duration = Duration.ofSeconds(2);
        Title title = Title.title(
                Component.text("Return to the living.").color(NamedTextColor.GOLD),
                Component.text("Blessed be the Skyburger.").color(NamedTextColor.RED),
                Title.Times.times(duration, duration, duration)
        );
        for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.showTitle(title);
        }
    }

    private void playReviveEffect() {
        player.playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 1.0f, 1.0f);

        revivePlayer(player);
        SwirlEffect swirlEffect = new SwirlEffect(player, Particle.TOTEM, 1);
        swirlEffect.runTaskTimer(plugin, 0L, 1L);

        this.cancel();
    }

    private void revivePlayer(Player player) {
        player.setGameMode(GameMode.SURVIVAL);

        player.setHealth(10);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.teleport(location);

        plugin.getPunishmentManager().decreaseMaxHealth(2);
    }
}
