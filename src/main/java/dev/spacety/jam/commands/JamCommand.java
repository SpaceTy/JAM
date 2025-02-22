package dev.spacety.jam.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dev.spacety.jam.gui.pages.IndexPage;
import dev.spacety.jam.util.PlayerMessage;
import dev.spacety.jam.JAM;
import net.kyori.adventure.audience.Audience;

public class JamCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            PlayerMessage.send((Audience) sender, JAM.getString("messages.players-only"));
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("jam.admin") && !player.isOp()) {
            PlayerMessage.send((Audience) player, JAM.getString("messages.no-permission"));
            return true;
        }

        player.openInventory(new IndexPage(player).getInventory());
        return true;
    }
}