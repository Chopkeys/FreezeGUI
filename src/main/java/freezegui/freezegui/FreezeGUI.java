package freezegui.freezegui;

import freezegui.freezegui.commands.UnFreezeCommand;
import freezegui.freezegui.events.EventsHandler;
import org.bukkit.plugin.java.JavaPlugin;
import freezegui.freezegui.commands.FreezeCommand;
public final class FreezeGUI extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("unfreeze").setExecutor(new UnFreezeCommand());

        getServer().getPluginManager().registerEvents(new EventsHandler(this), this);
    }

    @Override
    public void onDisable() {
        System.out.println("FreezeGUI is shutting down");
    }
}
