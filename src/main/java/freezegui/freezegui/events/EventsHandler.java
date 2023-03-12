package freezegui.freezegui.events;
import freezegui.freezegui.FreezeGUI;
import freezegui.freezegui.util.FreezeFunc;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import freezegui.freezegui.commands.FreezeCommand;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class EventsHandler implements Listener {
    public static HashMap<UUID, Boolean> ifLeave = new HashMap<>();
    FreezeFunc Freeze = new FreezeFunc();
    private final FreezeGUI plugin;
    public EventsHandler(FreezeGUI freezeGUI) {
        this.plugin = freezeGUI;
    }

    @EventHandler
    public void onMenuInteract(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("You have been frozen by staff")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        HumanEntity p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if (FreezeCommand.hm.get(uuid) != Boolean.TRUE) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onLeaveIfFrozen(PlayerQuitEvent e) {
        UUID uuid = (e.getPlayer()).getUniqueId();
        if ((FreezeCommand.hm.get(uuid)) != Boolean.TRUE) return;
        if(ifLeave.get(uuid) != null) {
            ifLeave.replace(uuid, true);
        }
        ifLeave.put(uuid, true);
    }
    @EventHandler
    public void inventoryClose(InventoryCloseEvent e) {
        HumanEntity p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if ((ifLeave.get(uuid)) == Boolean.TRUE) return;
        if (FreezeCommand.hm.get(uuid) != Boolean.TRUE) return;
        if(e.getView().getTitle().equalsIgnoreCase("You have been frozen by staff"))  {

            new BukkitRunnable() {
                @Override
                public void run() {
                    p.openInventory(e.getInventory());
                    if(plugin.config.getBoolean("logFreezes")) {
                        System.out.println((p.getName()) + "'s frozen screen has been re-opened (They tried to close it)");
                    }
                }
            }.runTask(this.plugin);
        }
    }
    @EventHandler
    public void onRejoinIfFrozen(PlayerJoinEvent e) {
        UUID uuid = (e.getPlayer()).getUniqueId();
        ifLeave.replace(uuid, false);
        if ((FreezeCommand.hm.get(uuid)) != Boolean.TRUE) return;
        new BukkitRunnable() {
            @Override
            public void run() {
                Freeze.Freeze((e.getPlayer()), plugin.config.getBoolean("logFreezes"), true);
            }
        }.runTask(this.plugin);
    }
}
