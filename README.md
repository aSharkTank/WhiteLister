
# WhiteLister

The ultimate whitelisting plugin that allows you to easily manage the whitelist!
## Features
This plugin allows you to fully manage every aspect of your whitelist management, be it allowing players to add others to the whitelist, remove them or get a list of all the players in the whitelist.

This plugin also allows for certain configurations to be made. For example, you may control how many players each player can add to the whitelist, or whether to enforce the whitelist (kick players from the server if they're not on the whitelist).

More features and configurations are in plan, and any suggestions will be appreciated!


## Commands

The main command for the plugin is ```/wl```, followed by 3 possible subcommands:

```/wl add <player>``` | this command will add a player to the whitelist

```/wl remove <player>``` | this command will remove a player from the whitelist

```/wl list``` | this command will list all the whitelisted players and the amount of the players on the whitelist.

```/wl setlimit <number>``` | this command will allow a player to set the whitelist invites limit

```/wl setenforced <boolean>``` | this command will allow a player to set the whitelist invites limit

```/wl setdebug <boolean>``` | this command allows a player to set the plugin's debug mode

```/wl reload``` | this command will reload the plugin's folder!


## Permissions

There are 8 permissions for this plugin:
  
```wl.add``` | 
      ```allows a player to add a player to the whitelist```

```wl.remove``` |
      ```allows a player to remove a player from the whitelist```

```wl.list``` |
      ```allows a player to list all players on the whitelist```

```wl.setlimit``` |
      ```allows a player to set the whitelist invites limit```

```wl.setenforced``` |
      ```allows a player to set the whitelist enforcement```

```wl.setdebug``` |
      ```allows a player to set the plugin's debug mode```

```wl.reload``` |
      ```allows a player to reload the plugin's configuration files```

```wl.bypasslimit``` |
      ```allows a player to bypass the whitelist invites limit```

```wl.*``` | 
      ```allows a player to use all of the plugin's commands```


## config.yml

```
# This option allows you to enable/disable the plugin's debug mode which prints out extra information in the console, not needed unless encountering very specific issues
debug: false
# This option will allow you to limit how many players each player (with the wl.add permission) can invite (add).
# Set to '0' to disable. Must be an Integer (whole number).
MaxWhitelistInvitesPerPlayer: 5
# This option will allow you to toggle whitelist enforcing (whether to kick active players from the whitelist once they're removed)
WhitelistEnforcingEnabled: false
```


## Installation

If your server is running on Spigot or any similar server APIs (PaperMC, CraftBukkit), you will need to download the jar file and drag it into your 'plugins' folder located in the server folder.
Then, restart the server to allow the configuration files to be loaded.
And done! 
Make sure to report any bugs you find and I'm always willing to know how I can improve on the plugin!
    
