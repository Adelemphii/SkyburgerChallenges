package me.adelemphii.skyburgerchallenges;

import me.adelemphii.skyburgerchallenges.listeners.ExperienceListener;
import me.adelemphii.skyburgerchallenges.managers.ExperienceManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkyburgerChallenges extends JavaPlugin {

    private ExperienceManager experienceManager;

    @Override
    public void onEnable() {
        this.experienceManager = new ExperienceManager();

        registerEvents();
    }

    @Override
    public void onDisable() {
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new ExperienceListener(this), this);
    }

    public ExperienceManager getExperienceManager() {
        return experienceManager;
    }
}
