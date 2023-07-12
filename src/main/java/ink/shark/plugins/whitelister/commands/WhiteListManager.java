package ink.shark.plugins.whitelister.commands;

import ink.shark.plugins.whitelister.util.Common;
import ink.shark.plugins.whitelister.util.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class WhiteListManager {
    private static final HashMap<UUID, Integer> invites = new HashMap<>();

    public static void addPlayer(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("wl.add")) {
            Common.sendNoPermissionMessage(sender);
            return;
        }
        if (getWhitelistings(sender) >= (int) ConfigHandler.getFile("config.yml").get("MaxWhitelistInvitesPerPlayer")) {
            Common.sendTranslatedMessage(sender, "&cYou have reached the maximum number of whitelist invites you can send.");
            return;
        }
        if (args.length >= 2) {
            whitelistFromConsole(sender, args[1], true);
        } else {
            Common.sendTranslatedMessage(sender, "&cYou must specify a player to add to the whitelist.");
        }
    }

    public static void removePlayer(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("wl.remove")) {
            Common.sendNoPermissionMessage(sender);
            return;
        }
        if (args.length >= 2) {
            whitelistFromConsole(sender, args[1], false);
        } else {
            sender.sendMessage("You must specify a player to remove from the whitelist.");
        }
    }

    public static void listPlayers(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("wl.list")) {
            Common.sendNoPermissionMessage(sender);
            return;
        }
        sender.sendMessage("Whitelist ( %whitelisted_amount% ):".replaceFirst("%whitelisted_amount%", String.valueOf(Bukkit.getWhitelistedPlayers().size())));
        for (OfflinePlayer player : sender.getServer().getWhitelistedPlayers()) {
            sender.sendMessage(String.format("  - %s", player.getName()));
        }
    }

    @Deprecated
    private static void whitelistPlayer(CommandSender sender, String[] args, boolean whitelist) {
        Player player = Bukkit.getPlayer(args[1]);
        if (player == null) {
            player = Bukkit.getOfflinePlayer(UUID.fromString(args[1].toString())).getPlayer();
        }
        if (whitelist && Bukkit.getWhitelistedPlayers().contains(player)) {
            Common.sendTranslatedMessage(sender, "&cPlayer is already whitelisted.");
            return;
        }
        if (player != null) {
            player.setWhitelisted(whitelist);
            Bukkit.reloadWhitelist();
        } else {
            Common.log("Player not found.");
            Common.sendTranslatedMessage(sender, "&cPlayer not found.");
            return;
        }
        if (whitelist) {
            sender.sendMessage("Added " + args[1] + " to the whitelist.");
            Common.log(args[1] + " was added to the whitelist by " + sender.getName());
        } else {
            sender.sendMessage("Removed " + args[1] + " from the whitelist.");
            Common.log(args[1] + " was removed from the whitelist by " + sender.getName());
        }
    }

    public static int getWhitelistings(CommandSender sender) {
        int allowedInvites = (int) ConfigHandler.getFile("config.yml").get("MaxWhitelistInvitesPerPlayer");
        if (!(sender instanceof Player)
                || sender.hasPermission("wl.bypasslimit")
                || allowedInvites <= 0)
            return allowedInvites - 1;
        Player player = (Player) sender;
        if (!invites.containsKey(player.getUniqueId())) invites.put(player.getUniqueId(), 0);
        return invites.get(player.getUniqueId());
    }

    public static void addWhitelistings(CommandSender sender) {
        if (!(sender instanceof Player) || sender.hasPermission("wl.bypasslimit")) return;
        Player player = (Player) sender;
        if (!invites.containsKey(player.getUniqueId())) invites.put(player.getUniqueId(), 0);
        invites.put(player.getUniqueId(), invites.get(player.getUniqueId()) + 1);
    }

    public static void setWhitelistings(CommandSender sender, int whitelistings) {
        if (!(sender instanceof Player) || sender.hasPermission("wl.bypasslimit")) return;
        Player player = (Player) sender;
        invites.put(player.getUniqueId(), whitelistings);
    }

    private static void whitelistFromConsole(CommandSender sender, String username, boolean whitelist) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(username);
        if (whitelist && Bukkit.getWhitelistedPlayers().contains(player)) {
            Common.sendTranslatedMessage(sender, "&cPlayer is already whitelisted.");
            return;
        }

        Plugin plugin = Bukkit.getPluginManager().getPlugin("WhiteLister");
        if (plugin == null) {
            Common.log("Plugin not found.");
            Common.sendTranslatedMessage(sender, "&cPlugin not found.");
            return;
        }
        String command = whitelist ? "whitelist add " + username : "whitelist remove " + username;
        Bukkit.getScheduler().callSyncMethod(plugin, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command));
        Bukkit.reloadWhitelist();

        if (whitelist) {
            addWhitelistings(sender);
            Common.sendTranslatedMessage(sender, "Added &7" + username + "&r to the whitelist.");
            Common.log(username + " was added to the whitelist by " + sender.getName());
        } else {
            Common.sendTranslatedMessage(sender, "Removed &7" + username + "&r from the whitelist.");
            Common.log(username + " was removed from the whitelist by " + sender.getName());
        }
    }
}
