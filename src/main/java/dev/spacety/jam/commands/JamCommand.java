package dev.spacety.jam.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JamCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players!");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("jam.admin") && !player.isOp()) {
            player.sendMessage("§cYou don't have permission to use this command!");
            return true;
        }

        // TODO: Add GUI opening logic here
        player.sendMessage("§aOpening JAM admin panel...");
        return true;
    }
}