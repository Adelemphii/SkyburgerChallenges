package me.adelemphii.skyburgerchallenges.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import me.adelemphii.skyburgerchallenges.SkyburgerChallenges;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

@CommandAlias("skyburger|sb")
public class CommandSkyburger extends BaseCommand {

    private final SkyburgerChallenges plugin;

    public CommandSkyburger(SkyburgerChallenges plugin) {
        this.plugin = plugin;
    }

    @Subcommand("set levels")
    @CommandPermission("skyburger.admin")
    public void onSetLevels(Player player, int levels) {
        plugin.getExperienceManager().setLevels(levels);
        player.sendMessage(Component.text("SkyburgerChallenges: Set levels to " + levels).color(NamedTextColor.RED));
    }
}
