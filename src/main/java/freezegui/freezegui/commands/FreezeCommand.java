package freezegui.freezegui.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class FreezeCommand implements CommandExecutor {
    public static HashMap<Player, Boolean> hm = new HashMap<Player, Boolean>();

    @Override

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /freeze <player>");
        }


        Player p = Bukkit.getPlayer(args[0]);

        if(p == null) sender.sendMessage(ChatColor.RED + "The specified player is either not online or doesn't exist");
        Inventory GUI = Bukkit.createInventory(p, 9, "You have been frozen by staff");

        ItemStack item1 = new ItemStack(Material.PAPER, 1);
        ItemMeta item1_meta = item1.getItemMeta();
        item1_meta.setDisplayName("You have been frozen by staff, please look at your discord!");
        item1.setItemMeta(item1_meta);
        GUI.addItem(item1);

        System.out.println((p.getName()) + " has been frozen");
        sender.sendMessage(ChatColor.GREEN + (p.toString()) + "has been frozen");

        hm.put(p,true);

        p.openInventory(GUI);

        return true;
    }
}
