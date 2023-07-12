package ink.shark.plugins.whitelister.commands;

import ink.shark.plugins.whitelister.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {
    private static final String[] subcommands = {"add", "remove", "list"};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ("wl".equalsIgnoreCase(command.getName())) {
            if (args.length == 0) {
                sender.sendMessage("You must specify a subcommand.");
                return false;
            }
            if ("add".equalsIgnoreCase(args[0])) WhiteListManager.addPlayer(sender, command, label, args);
            else if ("remove".equalsIgnoreCase(args[0])) WhiteListManager.removePlayer(sender, command, label, args);
            else if ("list".equalsIgnoreCase(args[0])) WhiteListManager.listPlayers(sender, command, label, args);
            else {
                sender.sendMessage("Invalid subcommand.");
                return false;
            }
        }
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            final List<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], Arrays.asList(subcommands), completions);
            return completions;
        }
        if (args.length == 2) {
            final List<String> completions = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                StringUtil.copyPartialMatches(args[1], Collections.singletonList(player.getName()), completions);
            }
            return completions;
        }
        return null;
    }
}
