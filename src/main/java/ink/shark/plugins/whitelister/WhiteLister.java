package ink.shark.plugins.whitelister;

import ink.shark.plugins.whitelister.commands.CommandManager;
import ink.shark.plugins.whitelister.util.Common;
import ink.shark.plugins.whitelister.util.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class WhiteLister extends JavaPlugin {
    private ConfigHandler configFile;
    private final HashMap<String, Object> defaults = new HashMap<String, Object>() {{
        put("WhitelistEnforcingEnabled", false);
        put("MaxWhitelistInvitesPerPlayer", 5);
    }};

    @Override
    public void onEnable() {
        configFile = new ConfigHandler("config.yml");
        setDefaults(defaults);
        configFile.save();
        configFile.getConfig().options().copyDefaults(true);

        getCommand("wl").setExecutor(new CommandManager());
        getCommand("wl").setTabCompleter(new CommandManager());

        Common.log(
                "&6" + this.getDescription().getName() + " &c"
                        + this.getDescription().getVersion()
                        + "&a was enabled. &eYou can configure the settings in the &f'config.yml' &efile.");

        try {
            Bukkit.setWhitelistEnforced((Boolean) configFile.get("WhitelistEnforcingEnabled"));
        } catch (Exception e) {
            Common.log("&cError setting config option 'WhitelistEnforcingEnabled'");
        }
    }

    @Override
    public void onDisable() {
        Common.log("&7Shutting down WhiteLister...");
        Common.log("&7Saving config...");
        configFile.save();
        Common.log("&7Reloading whitelist...");
        Bukkit.reloadWhitelist();
    }

    private void setDefaults(HashMap<String, Object> defaults) {
        for (String path : defaults.keySet()) {
            if (configFile.get(path) == null) {
                configFile.addDefault(path, defaults.get(path));
            }
        }
    }
}
