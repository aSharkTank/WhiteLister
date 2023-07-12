package ink.shark.plugins.whitelister.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {
    public Color() {
    }

    public static String translate(String path) {
        return translateColorCodes(translateHexColorCodes(path));
    }

    public static String translateColorCodes(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<String> translateColorCodes(String... stringsList) {
        List<String> updatedList = new ArrayList<>();
        for (String string : stringsList) {
            updatedList.add(Color.translateColorCodes(string));
        }
        return updatedList;
    }

    public static String translateHexColorCodes(String message) {
        Pattern hexPattern = Pattern.compile("([A-Fa-f0-9]{6})");
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 32);

        while (matcher.find()) {
            String group = matcher.group(1);
            char var10002 = group.charAt(0);
            matcher.appendReplacement(buffer, "§x§" + var10002 + "§" + group.charAt(1) + "§" + group.charAt(2) + "§" + group.charAt(3) + "§" + group.charAt(4) + "§" + group.charAt(5));
        }

        return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
    }

    public static String RED(String string) {
        return ChatColor.RED + string;
    }

    public static String GRAY(String string) {
        return ChatColor.GRAY + string;
    }

    public static String YELLOW(String string) {
        return ChatColor.YELLOW + string;
    }

    public static String WHITE(String string) {
        return ChatColor.WHITE + string;
    }

    public static String DARK_RED(String string) {
        return ChatColor.DARK_RED + string;
    }

    public static String GREEN(String string) {
        return ChatColor.GREEN + string;
    }

    public static String DARK_GREEN(String string) {
        return ChatColor.DARK_GREEN + string;
    }

    public static String DARK_AQUA(String string) {
        return ChatColor.DARK_AQUA + string;
    }

    public static String DARK_PURPLE(String string) {
        return ChatColor.DARK_PURPLE + string;
    }

    public static String DARK_BLUE(String string) {
        return ChatColor.DARK_BLUE + string;
    }

    public static String DARK_GRAY(String string) {
        return ChatColor.DARK_GRAY + string;
    }

    public static String AQUA(String string) {
        return ChatColor.AQUA + string;
    }

    public static String BLUE(String string) {
        return ChatColor.BLUE + string;
    }

    public static String GOLD(String string) {
        return ChatColor.GOLD + string;
    }

    public static String LIGHT_PURPLE(String string) {
        return ChatColor.LIGHT_PURPLE + string;
    }

    public static String BLACK(String string) {
        return ChatColor.BLACK + string;
    }

    public static String BOLD(String string) {
        return ChatColor.BOLD + string;
    }

    public static String ITALIC(String string) {
        return ChatColor.ITALIC + string;
    }

    public static String STRIKETHROUGH(String string) {
        return ChatColor.STRIKETHROUGH + string;
    }

    public static String MAGIC(String string) {
        return ChatColor.MAGIC + string;
    }

    public static String RANDOM(String string) {
        ChatColor var10000 = ChatColor.getByChar(Integer.toHexString((new Random()).nextInt(16)));
        return var10000 + string;
    }
}
