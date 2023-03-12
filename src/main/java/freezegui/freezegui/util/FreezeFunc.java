package freezegui.freezegui.util;
import freezegui.freezegui.FreezeGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FreezeFunc {
    FileConfiguration config = FreezeGUI.getPlugin(FreezeGUI.class).getConfig();
    public void Freeze(HumanEntity p, Boolean logToSys, Boolean rejoin) {
        Inventory GUI = Bukkit.createInventory(p, 9, "You have been frozen by staff");
        final ConfigurationSection guiItemSection = config.getConfigurationSection("GUIItems");

        for (String key : guiItemSection.getKeys(false)) {

            final ConfigurationSection currentSection = guiItemSection.getConfigurationSection(key);

            final String name = currentSection.getString("name");

            ItemStack item1 = new ItemStack((Material.matchMaterial(((currentSection.getString("type").toUpperCase())))), (currentSection.getInt("amount")));
            ItemMeta item1_meta = item1.getItemMeta();
            item1_meta.setDisplayName(currentSection.getString("name"));
            item1.setItemMeta(item1_meta);
            GUI.setItem((currentSection.getInt("placeInGUI")), item1);

        }

        p.openInventory(GUI);

        if(!logToSys) return;
        if(rejoin) {
            System.out.println((p.getName()) + "'s frozen screen has been re-applied (They left)");
        } else {
            System.out.println((p.getName()) + "'s frozen screen has been applied (Command)");
        }

    }
}
