package me.adelemphii.skyburgerchallenges.utility;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.objects.effects.LightningEffect;
import me.adelemphii.skyburgerchallenges.objects.effects.ReviveEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class EffectUtility {

    public static void revivePlayer(Player target, World overworld, SkyburgerChallenges plugin) {
        Location reviveLocation = overworld.getWorldBorder().getCenter().toHighestLocation();

        double radius = 2;
        LightningEffect lightningEffect = new LightningEffect(reviveLocation, radius, location -> {
            ReviveEffect reviveEffect = new ReviveEffect(target, reviveLocation, plugin);
            reviveEffect.runTask(plugin);
        });
        lightningEffect.runTaskTimer(plugin, 0L, 5L);
    }
}
