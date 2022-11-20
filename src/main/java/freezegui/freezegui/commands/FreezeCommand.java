package freezegui.freezegui.commands;
import freezegui.freezegui.util.FreezeFunc;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.UUID;

public class FreezeCommand implements CommandExecutor {
    FreezeFunc Freeze = new FreezeFunc();
    public static HashMap<UUID, Boolean> hm = new HashMap<>();

    @Override

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /freeze <player>");
        }

        Player p = Bukkit.getPlayer(args[0]);
        if(p == null) sender.sendMessage(ChatColor.RED + "The specified player is either not online or doesn't exist");
        UUID uuid = p.getUniqueId();

        Freeze.Freeze(p, true);

        sender.sendMessage(ChatColor.GREEN + p.getName() + " has been frozen");

        hm.put(uuid,true);

        return true;
    }
}
