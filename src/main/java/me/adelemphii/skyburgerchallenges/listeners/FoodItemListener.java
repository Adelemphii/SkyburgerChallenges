package me.adelemphii.skyburgerchallenges.listeners;

import me.adelemphii.skyburgerchallenges.food.object.FoodItem;
import me.adelemphii.skyburgerchallenges.utility.FoodUtility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class FoodItemListener implements Listener {

    @EventHandler
    public void onFoodConsume(PlayerItemConsumeEvent event) {
        ItemStack dropped = event.getItem();

        FoodItem food = FoodUtility.getFoodFromItemStack(dropped);
        if(food == null) {
            return;
        }

        food.eat(event);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        ItemStack dropped = event.getItemDrop().getItemStack();

        FoodItem food = FoodUtility.getFoodFromItemStack(dropped);
        if(food == null) {
            return;
        }

        food.drop(event);
    }
}
