package me.adelemphii.skyburgerchallenges.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HungerListener implements Listener {

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent event) {
        Bukkit.broadcast(Component.text(event.getFoodLevel()));
        if(event.getFoodLevel() <= 6) {
            event.getEntity().addPotionEffect(
                    new PotionEffect(PotionEffectType.WEAKNESS, PotionEffect.INFINITE_DURATION, 1));
        } else {
            event.getEntity().removePotionEffect(PotionEffectType.WEAKNESS);
        }
    }
}
