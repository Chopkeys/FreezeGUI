package freezegui.freezegui;

import freezegui.freezegui.commands.UnFreezeCommand;
import freezegui.freezegui.events.EventsHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import freezegui.freezegui.commands.FreezeCommand;
public final class FreezeGUI extends JavaPlugin {
    public FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.addDefault("logFreezes", true);
        config.addDefault("logReFreezes", true);
        config.addDefault("GUIItems.item1.type", "Paper");
        config.addDefault("GUIItems.item1.name", "You have been frozen by staff, please look at your discord!");
        config.addDefault("GUIItems.item1.amount", 1);
        config.addDefault("GUIItems.item1.placeInGUI", 4);
        config.options().copyDefaults(true);
        saveConfig();

        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("unfreeze").setExecutor(new UnFreezeCommand());

        getServer().getPluginManager().registerEvents(new EventsHandler(this), this);
    }

    @Override
    public void onDisable() {
        System.out.println("FreezeGUI is shutting down");
    }
}
