package me.adelemphii.skyburgerchallenges.objects.effects;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SwirlEffect extends BukkitRunnable {

    private final Player player;
    private final Particle particle;
    private final double radius;
    private double angle = 0;

    public SwirlEffect(Player player, Particle particle, double radius) {
        this.player = player;
        this.particle = particle;
        this.radius = radius;
    }

    @Override
    public void run() {
        Location playerLocation = player.getLocation();
        double x = playerLocation.getX() + radius * Math.cos(angle);
        double z = playerLocation.getZ() + radius * Math.sin(angle);
        double y = playerLocation.getY() + 1.5;

        Location particleLocation = new Location(player.getWorld(), x, y, z);
        player.getWorld().spawnParticle(particle, particleLocation, 1, 0, 0, 0, 0);

        angle += Math.PI / 16;
        if (angle >= 2 * Math.PI) {
            this.cancel();
        }
    }
}
