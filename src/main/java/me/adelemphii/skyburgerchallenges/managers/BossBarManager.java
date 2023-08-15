package me.adelemphii.skyburgerchallenges.managers;

import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossBarManager {

    private final ExperienceManager experienceManager;

    private final SkyburgerChallenges plugin;
    private BossBar bossBar;

    public BossBarManager(SkyburgerChallenges plugin, ExperienceManager experienceManager) {
        this.plugin = plugin;
        this.experienceManager = experienceManager;
    }

    public void createBossBar() {
        bossBar = plugin.getServer().createBossBar("Total Levels: " + experienceManager.getLevels(),
                BarColor.GREEN, BarStyle.SOLID);

        Bukkit.getOnlinePlayers().forEach(this::addPlayerToBossBar);

        updateBossBar();
    }

    public void updateBossBar() {
        if(bossBar != null) {
            bossBar.setTitle("Total Levels: " + experienceManager.getLevels());
            bossBar.setProgress(1.0d);
        }
    }

    public void addPlayerToBossBar(Player player) {
        if(bossBar != null) {
            bossBar.addPlayer(player);
            bossBar.setVisible(true);
        }
    }

    public void removePlayerFromBossBar(Player player) {
        if(bossBar != null) {
            bossBar.removePlayer(player);
            bossBar.setVisible(false);
        }
    }
}
