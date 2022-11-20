package freezegui.freezegui.events;
import freezegui.freezegui.FreezeGUI;
import freezegui.freezegui.util.FreezeFunc;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import freezegui.freezegui.commands.FreezeCommand;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import java.util.UUID;

public class EventsHandler implements Listener {
    FreezeFunc Freeze = new FreezeFunc();
    private final FreezeGUI plugin;
    public EventsHandler(FreezeGUI freezeGUI) {
        this.plugin = freezeGUI;
    }

    @EventHandler
    public void onMenuInteract(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("You have been frozen by staff")) {
            if(e.getCurrentItem() != null && (e.getCurrentItem()).getType().equals(Material.PAPER)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMenuInteract(PlayerMoveEvent e) {
        HumanEntity p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if (FreezeCommand.hm.get(uuid) != Boolean.TRUE) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onCloseInv(InventoryCloseEvent e) {
        HumanEntity p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if (FreezeCommand.hm.get(uuid) != Boolean.TRUE) return;
        if(e.getView().getTitle().equalsIgnoreCase("You have been frozen by staff")) {
            Inventory inv = e.getInventory();

            Bukkit.getScheduler().runTaskLater(plugin, () -> p.openInventory(inv), 1L);
            System.out.println("Frozen Inventory re-applied to " + (p.getName()));


        }
    }
    @EventHandler
    public void onRejoinIfFrozen(PlayerJoinEvent e) {
        HumanEntity p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if ((FreezeCommand.hm.get(uuid)) != Boolean.TRUE) return;
        Freeze.Freeze(p, true);
    }

}
