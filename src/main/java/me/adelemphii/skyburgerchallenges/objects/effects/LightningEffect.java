package me.adelemphii.skyburgerchallenges.objects.effects;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

public class LightningEffect extends BukkitRunnable {

    private final Location centerLocation;
    private final World world;
    private final double radius;
    private double angle = 0;

    private final Consumer<Location> completed;

    public LightningEffect(Location centerLocation, double radius, Consumer<Location> completed) {
        this.centerLocation = centerLocation;
        this.world = centerLocation.getWorld();
        this.radius = radius;
        this.completed = completed;
    }

    @Override
    public void run() {
        double x = centerLocation.getX() + radius * Math.cos(angle);
        double z = centerLocation.getZ() + radius * Math.sin(angle);
        double y = centerLocation.getY();

        Location strikeLocation = new Location(world, x, y, z);

        world.strikeLightningEffect(strikeLocation);

        angle += Math.PI / 8;
        if (angle >= 2 * Math.PI) {
            this.completed.accept(centerLocation);
            this.cancel();
        }
    }
}
