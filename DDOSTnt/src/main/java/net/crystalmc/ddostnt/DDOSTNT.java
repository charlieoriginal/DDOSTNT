package net.crystalmc.ddostnt;

import net.crystalmc.ddostnt.commands.GiveDdosTntCommand;
import net.crystalmc.ddostnt.handler.DDOSEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DDOSTNT extends JavaPlugin {

    private GiveDdosTntCommand giveDdosTntCommand;
    private DDOSEventListener ddosEventListener;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.giveDdosTntCommand = new GiveDdosTntCommand();
        this.ddosEventListener = new DDOSEventListener();

        getCommand("giveddostnt").setExecutor(giveDdosTntCommand);
        getServer().getPluginManager().registerEvents(ddosEventListener, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
