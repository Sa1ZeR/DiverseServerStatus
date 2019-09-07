package me.Sa1ZeR_.DiverseServerStatus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender.hasPermission("serverstatus.admin")) {
            if (args.length == 0) {
                ServerStatus.instance.getCfg().getString("messages.noArgs");
                return false;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                ServerStatus.instance.onDisable();
                ServerStatus.instance.onEnable();
                ServerStatus.instance.getCfg().getString("messages.reload");
            }
        } else {
            ServerStatus.instance.getCfg().getString("messages.noPerm");
            return false;
        }
        return false;
    }
}
