package freezegui.freezegui.util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FreezeFunc {
    public void Freeze(HumanEntity p, Boolean logToSys) {
        Inventory GUI = Bukkit.createInventory(p, 9, "You have been frozen by staff");

        ItemStack item1 = new ItemStack(Material.PAPER, 1);
        ItemMeta item1_meta = item1.getItemMeta();
        item1_meta.setDisplayName("You have been frozen by staff, please look at your discord!");
        item1.setItemMeta(item1_meta);
        GUI.setItem(4, item1);

        if(logToSys) {
            System.out.println((p.getName()) + "'s frozen screen has been applied");
        }

        p.openInventory(GUI);
    }
}
