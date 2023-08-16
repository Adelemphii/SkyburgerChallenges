package me.adelemphii.skyburgerchallenges.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import me.adelemphii.skyburgerchallenges.objects.effects.PentagramEffect;
import me.adelemphii.skyburgerchallenges.utility.EffectUtility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

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

    @Subcommand("restore maxhealth")
    @CommandPermission("skyburger.admin")
    public void onRestoreMaxhealth(Player player) {
        plugin.getPunishmentManager().setMaxHealth(20);
        player.sendMessage(Component.text("SkyburgerChallenges: Restored max health to 20").color(NamedTextColor.RED));
    }

    @Subcommand("revive")
    @CommandCompletion("@dead_players")
    @CommandPermission("skyburger.admin")
    public void onRevive(Player player, Player target) {
        if(target.getGameMode() != GameMode.SPECTATOR) {
            player.sendMessage(Component.text("That player is not in dead.").color(NamedTextColor.RED));
            return;
        }

        EffectUtility.revivePlayer(target, player.getWorld(), plugin);
    }

    @Subcommand("pentagram")
    @CommandPermission("skyburger.admin")
    public void onPentagram(Player player) {
        PentagramEffect pentagramEffect = new PentagramEffect(player, 1); // Adjust the radius as needed
        pentagramEffect.runTaskTimer(plugin, 0, 1L);
    }

    @Subcommand("worldborder center")
    @CommandPermission("skyburger.admin")
    public void onCenter(Player player) {
        plugin.getExperienceManager().getWorldBorderManager().setCenterLocation(player.getLocation());
        player.sendMessage(Component.text("SkyburgerChallenges: Center set!").color(NamedTextColor.RED));
    }
}
