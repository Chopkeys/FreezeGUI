package freezegui.freezegui.events;

import freezegui.freezegui.FreezeGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import freezegui.freezegui.commands.FreezeCommand;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class EventsHandler implements Listener {
    private FreezeGUI plugin;
    public EventsHandler(FreezeGUI freezeGUI) {
        this.plugin = freezeGUI;
    }

    @EventHandler
    public void onMenuInteract(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("You have been frozen by staff")) {
            if(Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.PAPER)) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onCloseInv(InventoryCloseEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("You have been frozen by staff")) {
            HumanEntity p = e.getPlayer();
            Inventory inv = e.getInventory();
            boolean onOrOff = FreezeCommand.hm.get(p);
            if(onOrOff != true) {
                System.out.println((p.getName()) + " Has closed the inventory when they should have been able to");
            }
            System.out.println((p.getName()) + " Has closed the frozen inv when they shouldn't have");
            FreezeGUI JavaPlugin;

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                p.openInventory(inv);
            }, 1L);
            System.out.println("Frozen Inventory re-applied to " + (p.getName()));


        }
    }
    @EventHandler
    public void onRejoinIfFrozen(PlayerJoinEvent e) {
        HumanEntity p = e.getPlayer();
        boolean onOrOff = FreezeCommand.hm.get(p);
        if(onOrOff != true) return;
        System.out.println((p.getName()) + " Has joined while frozen, re-opening frozen screen");

        Inventory GUI = Bukkit.createInventory(p, 9, "You have been frozen by staff");

        ItemStack item1 = new ItemStack(Material.PAPER, 1);
        ItemMeta item1_meta = item1.getItemMeta();
        item1_meta.setDisplayName("You have been frozen by staff, please look at your discord!");
        item1.setItemMeta(item1_meta);
        GUI.addItem(item1);

        p.openInventory(GUI);
    }

}
