package me.adelemphii.skyburgerchallenges.food.object;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public abstract class FoodItem implements Cloneable {

    protected final Component name;
    protected final String tag;

    protected List<Component> lore;
    protected final Material material;
    protected ShapelessRecipe shapelessRecipe;

    public FoodItem() {
        this.name = setName();
        this.lore = setLore();
        this.material = setMaterial();
        this.tag = setTag();
        this.shapelessRecipe = setShapedRecipe();

        if(shapelessRecipe != null) {
            if(Bukkit.getRecipe(shapelessRecipe.getKey()) != null) {
                Bukkit.removeRecipe(shapelessRecipe.getKey());
            }
            Bukkit.addRecipe(shapelessRecipe);
        }
    }

    protected abstract Component setName();

    protected abstract List<Component> setLore();

    protected abstract Material setMaterial();

    protected abstract String setTag();

    protected abstract ShapelessRecipe setShapedRecipe();

    public Component getName() {
        return name;
    }

    public List<Component> getLore() {
        return lore;
    }

    public Material getMaterial() {
        return material;
    }

    public String getTag() {
        return tag;
    }

    public ShapelessRecipe getShapelessRecipe() {
        return shapelessRecipe;
    }

    public abstract void eat(PlayerItemConsumeEvent event);
    public abstract void drop(PlayerDropItemEvent event);

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();

        meta.displayName(name);
        meta.lore(lore);

        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(SkyburgerChallenges.getInstance().getFoodManager().getNamespacedKey(), PersistentDataType.STRING, tag);

        itemStack.setItemMeta(meta);

        itemStack.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        return itemStack;
    }

    @Override
    public FoodItem clone() {
        try {
            return (FoodItem) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
