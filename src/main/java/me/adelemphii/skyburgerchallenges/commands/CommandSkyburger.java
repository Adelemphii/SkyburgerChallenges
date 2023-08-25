package me.adelemphii.skyburgerchallenges.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.managers.ExperienceManager;
import me.adelemphii.skyburgerchallenges.utility.EffectUtility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.stream.Collectors;

@CommandAlias("skyburger|sb")
public class CommandSkyburger extends BaseCommand {

    private final SkyburgerChallenges plugin;

    public CommandSkyburger(SkyburgerChallenges plugin) {
        this.plugin = plugin;

        plugin.getPaperCommandManager().getCommandCompletions().registerCompletion("dead_players", (foo) ->
                Bukkit.getOnlinePlayers()
                        .stream()
                        .filter(player -> player.getGameMode() == GameMode.SPECTATOR)
                        .map(Player::getName)
                        .collect(Collectors.toList()));
    }

    @Subcommand("set levels")
    @CommandPermission("skyburger.admin")
    public void onSetLevels(Player player, int levels) {
        plugin.getExperienceManager().setLevels(levels);
        player.sendMessage(Component.text("SkyburgerChallenges: Set levels to " + levels).color(NamedTextColor.RED));
    }

    @Subcommand("set maxhealth")
    @CommandPermission("skyburger.admin")
    public void onSetMaxHealth(Player player, int maxHealth) {
        plugin.getPunishmentManager().setMaxHealth(maxHealth);
        player.sendMessage(Component.text("SkyburgerChallenges: Set max health to " + maxHealth).color(NamedTextColor.RED));
    }

    @Subcommand("set experiencecap")
    @CommandPermission("skyburger.admin")
    public void onSetExperienceCap(Player player, int level) {
        plugin.getExperienceManager().setRequiredExperienceCapLevel(level);
        player.sendMessage(Component.text("SkyburgerChallenges: Set experience cap level to " + level).color(NamedTextColor.RED));
    }

    @Subcommand("restore maxhealth")
    @CommandPermission("skyburger.admin")
    public void onRestoreMaxhealth(Player player) {
        plugin.getPunishmentManager().setMaxHealth(20);
        player.sendMessage(Component.text("SkyburgerChallenges: Restored max health to 20").color(NamedTextColor.RED));
    }

    @Subcommand("revive")
    @CommandCompletion("@dead_players")
    @CommandPermission("skyburger.admin")
    public void onRevive(Player player, @Flags("other,defaultself") @Optional Player target) {
        if(target.getGameMode() != GameMode.SPECTATOR) {
            player.sendMessage(Component.text("That player is not dead.").color(NamedTextColor.RED));
            return;
        }
        EffectUtility.revivePlayer(target, player.getWorld(), plugin);
    }

    @Subcommand("worldborder center")
    @CommandPermission("skyburger.admin")
    public void onCenter(Player player) {
        ExperienceManager experienceManager = plugin.getExperienceManager();
        World world = player.getWorld();
        Location playerLocation = player.getLocation();

        switch(world.getEnvironment()) {
            case NETHER: experienceManager.getWorldBorderManager()
                    .setNetherCenter(new Vector(
                            playerLocation.getBlockX(), playerLocation.getBlockY(), playerLocation.getBlockZ()
                    ));
            case NORMAL: experienceManager.getWorldBorderManager()
                    .setOverworldCenter(new Vector(
                            playerLocation.getBlockX(), playerLocation.getBlockY(), playerLocation.getBlockZ()
                    ));
        }
    }
}
