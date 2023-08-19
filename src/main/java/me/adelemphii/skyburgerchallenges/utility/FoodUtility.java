package me.adelemphii.skyburgerchallenges.utility;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.food.object.FoodItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class FoodUtility {

    public static FoodItem getFoodFromItemStack(ItemStack itemStack) {
        if(itemStack == null) {
            return null;
        }

        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
        String tag = pdc.get(SkyburgerChallenges.getInstance().getFoodManager().getNamespacedKey(), PersistentDataType.STRING);

        if(tag != null) {
            return SkyburgerChallenges.getInstance().getFoodManager().getFoodFromTag(tag);
        }
        return null;
    }

}
