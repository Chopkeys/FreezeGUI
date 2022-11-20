package freezegui.freezegui.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class UnFreezeCommand implements CommandExecutor {

    @Override

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /unfreeze <player>");
        }

        Player p = Bukkit.getPlayer(args[0]);
        if(p == null) sender.sendMessage(ChatColor.RED + "The specified player is either not online or doesn't exist");
        UUID uuid = p.getUniqueId();

        FreezeCommand.hm.put(uuid, false);
        sender.sendMessage(ChatColor.GREEN + (p.getName()) + " has been un-frozen");


        return true;
    }
}
