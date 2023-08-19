package me.adelemphii.skyburgerchallenges.managers;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.food.MilkBeer;
import me.adelemphii.skyburgerchallenges.food.object.FoodItem;
import me.adelemphii.skyburgerchallenges.utility.FoodUtility;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FoodManager {

    private final SkyburgerChallenges plugin;

    private final NamespacedKey namespacedKey;

    private final List<FoodItem> foodItemList;

    public FoodManager(SkyburgerChallenges plugin) {
        this.plugin = plugin;
        this.foodItemList = new ArrayList<>();

        this.namespacedKey = new NamespacedKey(SkyburgerChallenges.getInstance(), "sbchallenges");

        Bukkit.getScheduler().runTaskLater(plugin, this::registerFood, 10L);
    }

    private void registerFood() {
        this.foodItemList.add(new MilkBeer());

        StringBuilder sb = new StringBuilder("Registered Food: ");
        for(FoodItem foodItem : foodItemList) {
            PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();
            String name = serializer.serialize(foodItem.getName());

            sb.append(name).append(", ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        plugin.getLogger().info(sb.toString());
    }

    public List<FoodItem> getFoodItemList() {
        return foodItemList;
    }

    public FoodItem getFoodFromTag(String tag) {
        for(FoodItem foodItem : foodItemList) {
            if(tag.equals(foodItem.getTag())) {
                return foodItem.clone();
            }
        }
        return null;
    }

    public static boolean isHoldingFood(Player player) {
        return FoodUtility.getFoodFromItemStack(player.getInventory().getItemInMainHand()) != null
                || FoodUtility.getFoodFromItemStack(player.getInventory().getItemInOffHand()) != null;
    }

    public NamespacedKey getNamespacedKey() {
        return namespacedKey;
    }
}
