package me.adelemphii.skyburgerchallenges.managers;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.listeners.events.SkyburgerHealthChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class PunishmentManager {

    private int maxHealth = 20;

    private final SkyburgerChallenges plugin;

    public PunishmentManager(SkyburgerChallenges plugin) {
        this.plugin = plugin;

        new BukkitRunnable() {
            @Override
            public void run() {
                savePunishments();
            }                                             // 1 hour
        }.runTaskTimerAsynchronously(plugin, 0, 72000L);
    }

    public void setMaxHealth(int maxHealth) {
        int oldHealth = this.maxHealth;
        this.maxHealth = maxHealth;
        Bukkit.getPluginManager().callEvent(new SkyburgerHealthChangeEvent(oldHealth, maxHealth));
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void decreaseMaxHealth(int remove) {
        int oldHealth = this.maxHealth;
        maxHealth = maxHealth - remove;
        if(maxHealth <= 0) {
            maxHealth = 1;
        }
        Bukkit.getPluginManager().callEvent(new SkyburgerHealthChangeEvent(oldHealth, maxHealth));
    }

    public void increaseMaxHealth(int add) {
        int oldHealth = this.maxHealth;
        maxHealth = maxHealth + add;
        Bukkit.getPluginManager().callEvent(new SkyburgerHealthChangeEvent(oldHealth, maxHealth));
    }

    public void loadPunishments() {
        this.maxHealth = plugin.getConfig().getInt("max-health", 20);
    }

    public void savePunishments() {
        plugin.getConfig().set("max-health", this.maxHealth);
    }
}
