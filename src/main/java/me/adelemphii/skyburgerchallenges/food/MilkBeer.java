package me.adelemphii.skyburgerchallenges.food;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.food.object.FoodItem;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class MilkBeer extends FoodItem {

    @Override
    protected Component setName() {
        return Component.text("Milk Beer").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD)
                .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }

    @Override
    protected List<Component> setLore() {
        return List.of(
                Component.empty(),
                Component.text("Drink me. Now.").color(NamedTextColor.RED)
        );
    }

    @Override
    protected Material setMaterial() {
        return Material.MILK_BUCKET;
    }

    @Override
    protected String setTag() {
        return "milk_beer";
    }

    @Override
    protected ShapelessRecipe setShapedRecipe() {
        // milk, wheat, wheatseeds, barrel
        ShapelessRecipe shapeless = new ShapelessRecipe(SkyburgerChallenges.getInstance()
                .getFoodManager().getNamespacedKey(),
                getItemStack());

        shapeless.addIngredient(Material.MILK_BUCKET);
        shapeless.addIngredient(Material.WHEAT);
        shapeless.addIngredient(Material.WHEAT_SEEDS);
        shapeless.addIngredient(Material.BARREL);

        return shapeless;
    }

    @Override
    public void eat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(SkyburgerChallenges.getInstance(), () -> {
            player.addPotionEffects(List.of(
                    new PotionEffect(PotionEffectType.CONFUSION, 3600, 0),
                    new PotionEffect(PotionEffectType.SATURATION, 20, 20),
                    new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 1)
            ));

            PacketContainer fakeEvent = new PacketContainer(PacketType.Play.Server.GAME_STATE_CHANGE);
            fakeEvent.getGameStateIDs()
                    .write(0, 10);
            fakeEvent.getFloat().write(0, 0f);

            player.playSound(Sound.sound(Key.key("entity.elder_guardian.curse"),
                    Sound.Source.HOSTILE, 1f, 1f));
            SkyburgerChallenges.getInstance().getProtocolManager().sendServerPacket(player, fakeEvent);
        }, 10L);
    }

    @Override
    public void drop(PlayerDropItemEvent event) {

    }
}