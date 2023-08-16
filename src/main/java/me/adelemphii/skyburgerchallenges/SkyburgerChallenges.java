package me.adelemphii.skyburgerchallenges;

import co.aikar.commands.PaperCommandManager;
import me.adelemphii.skyburgerchallenges.commands.CommandSkyburger;
import me.adelemphii.skyburgerchallenges.listeners.*;
import me.adelemphii.skyburgerchallenges.managers.ExperienceManager;
import me.adelemphii.skyburgerchallenges.managers.PunishmentManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkyburgerChallenges extends JavaPlugin {

    private PunishmentManager punishmentManager;
    private ExperienceManager experienceManager;

    private PaperCommandManager paperCommandManager;

    @Override
    public void onEnable() {
        this.paperCommandManager = new PaperCommandManager(this);

        this.experienceManager = new ExperienceManager(this);
        experienceManager.loadExperience();
        experienceManager.getBossBarManager().createBossBar();

        this.punishmentManager = new PunishmentManager(this);
        punishmentManager.loadPunishments();

        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        if(experienceManager != null) {
            experienceManager.saveExperience();

            Bukkit.getOnlinePlayers().forEach(player -> experienceManager.getBossBarManager().removePlayerFromBossBar(player));
        }

        if(punishmentManager != null) {
            punishmentManager.savePunishments();
        }
    }

    private void registerCommands() {
        paperCommandManager.registerCommand(new CommandSkyburger(this));
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new JoinLeaveListener(this), this);

        pm.registerEvents(new ExperienceListener(this), this);
        pm.registerEvents(new PlayerDeathListener(this), this);
        pm.registerEvents(new HungerListener(), this);

        pm.registerEvents(new SkyburgerExperienceListener(this), this);
        pm.registerEvents(new SkyburgerHealthListener(this), this);
    }

    public PaperCommandManager getPaperCommandManager() {
        return paperCommandManager;
    }

    public ExperienceManager getExperienceManager() {
        return experienceManager;
    }

    public PunishmentManager getPunishmentManager() {
        return punishmentManager;
    }
}
