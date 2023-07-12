package ink.shark.plugins.whitelister.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Common {

    private static final String PREFIX = "&7[&c&lWhite&fLister&r&7] &r";

    public static void log(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(Color.translate(PREFIX + message));
    }

    public static void sendTranslatedMessage(CommandSender sendTo, String message) {
        sendTo.sendMessage(Color.translate(message));
    }

    public static void sendNoPermissionMessage(CommandSender sendTo) {
        sendTranslatedMessage(sendTo, "&cYou do not have permission to use this command.");
    }

    public static void sendBossBar(Player player, String text, BarColor color, BarStyle style) {
        BossBar bar = Bukkit.getServer().createBossBar(Color.translate(text), color, style, new BarFlag[0]);
        bar.addPlayer(player);
    }

    public static void playSound(Player player, Sound sound, int volume, int pitch) {
        player.playSound(player.getLocation(), sound, (float) volume, (float) pitch);
    }

    public static void playSound(Player player, Location loc, Sound sound, int volume, int pitch) {
        player.playSound(loc, sound, (float) volume, (float) pitch);
    }

    public static String getStringFromLocation(Location location) {
        if (location == null) {
            return "";
        } else {
            String var10000 = location.getWorld().getName();
            return var10000 + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch();
        }
    }

    public static Location getLocationFromString(String str) {
        if (str != null) {
            String[] split = str.split(":");
            if (split.length != 6) {
                return null;
            } else {
                World world = Bukkit.getServer().getWorld(split[0]);
                double x = Double.parseDouble(split[1]);
                double y = Double.parseDouble(split[2]);
                double z = Double.parseDouble(split[3]);
                float yaw = Float.parseFloat(split[4]);
                float pitch = Float.parseFloat(split[5]);
                return new Location(world, x, y, z, yaw, pitch);
            }
        } else {
            return null;
        }
    }
}
