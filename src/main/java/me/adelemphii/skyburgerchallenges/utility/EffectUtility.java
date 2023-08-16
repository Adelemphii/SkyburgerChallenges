package me.adelemphii.skyburgerchallenges.utility;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.objects.effects.LightningEffect;
import me.adelemphii.skyburgerchallenges.objects.effects.ReviveEffect;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class EffectUtility {

    public static final Map<Integer, Note> HAPPY_JINGLE = new HashMap<>();
    static {
        HAPPY_JINGLE.put(1, Note.natural(1, Note.Tone.C));
        HAPPY_JINGLE.put(2, Note.sharp(1, Note.Tone.D));
        HAPPY_JINGLE.put(3, Note.natural(1, Note.Tone.F));
    }

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
