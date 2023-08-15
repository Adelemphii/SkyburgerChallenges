package me.adelemphii.skyburgerchallenges.objects.effects;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class PentagramEffect extends BukkitRunnable {

    private final Player player;
    private final double radius;
    private final int lines = 5; // Number of lines in the pentagram
    private final int circlePoints = 36; // Number of points for the circular outline
    private double angle = 0;
    private final double rotationSpeed = Math.PI / 75; // Adjust rotation speed as needed (lower is faster)
    private int ticks = 0;

    public PentagramEffect(Player player, double radius) {
        this.player = player;
        this.radius = radius;
    }

    @Override
    public void run() {
        angle += rotationSpeed;
        if (angle >= 2 * Math.PI) {
            this.cancel(); // Stop the effect after one full rotation
        }

        Location playerLocation = player.getLocation();
        double centerX = playerLocation.getX();
        double centerZ = playerLocation.getZ();
        double centerY = playerLocation.getY() + 0.1; // Adjust particle height

        List<Location> corners = calculatePentagramCorners(centerX, centerY, centerZ, radius);

        for (int i = 0; i < lines; i++) {
            Location start = rotatePoint(corners.get(i), centerX, centerZ, angle);
            Location end = rotatePoint(corners.get((i + 2) % lines), centerX, centerZ, angle);

            spawnLineParticles(start, end);
        }

        Location[] circlePoints = calculateCirclePoints(centerX, centerY, centerZ, radius);
        spawnCircleParticles(circlePoints);

        ticks++;
        if (ticks >= 60 * 5) { // Duration of 5 seconds (5 seconds * 20 ticks per second)
            this.cancel(); // Stop the effect after 5 seconds
        }
    }

    private Location rotatePoint(Location point, double centerX, double centerZ, double angle) {
        double x = point.getX() - centerX;
        double z = point.getZ() - centerZ;

        double rotatedX = x * Math.cos(angle) - z * Math.sin(angle);
        double rotatedZ = x * Math.sin(angle) + z * Math.cos(angle);

        return new Location(player.getWorld(), rotatedX + centerX, point.getY(), rotatedZ + centerZ);
    }

    private List<Location> calculatePentagramCorners(double centerX, double centerY, double centerZ, double radius) {
        List<Location> corners = new ArrayList<>();
        double angleIncrement = 2 * Math.PI / lines;

        for (int i = 0; i < lines; i++) {
            double angle = i * angleIncrement;

            double x = centerX + radius * Math.cos(angle);
            double z = centerZ + radius * Math.sin(angle);

            corners.add(new Location(player.getWorld(), x, centerY, z));
        }

        return corners;
    }

    private Location[] calculateCirclePoints(double centerX, double centerY, double centerZ, double radius) {
        Location[] points = new Location[circlePoints];
        double angleIncrement = 2 * Math.PI / circlePoints;

        for (int i = 0; i < circlePoints; i++) {
            double angle = i * angleIncrement;

            double x = centerX + radius * Math.cos(angle);
            double z = centerZ + radius * Math.sin(angle);

            points[i] = new Location(player.getWorld(), x, centerY, z);
        }

        return points;
    }

    private void spawnLineParticles(Location start, Location end) {
        int points = 10; // Number of particles per line

        double dx = (end.getX() - start.getX()) / points;
        double dy = (end.getY() - start.getY()) / points;
        double dz = (end.getZ() - start.getZ()) / points;

        for (int i = 0; i < points; i++) {
            Location particleLocation = new Location(player.getWorld(),
                    start.getX() + dx * i, start.getY() + dy * i, start.getZ() + dz * i);

            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(232, 0, 0), 1);
            player.getWorld().spawnParticle(Particle.REDSTONE, particleLocation, 1, dustOptions);
        }
    }

    private void spawnCircleParticles(Location[] points) {
        for (Location point : points) {
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(97, 0, 0), 1);
            player.getWorld().spawnParticle(Particle.REDSTONE, point, 1, dustOptions);
        }
    }
}
