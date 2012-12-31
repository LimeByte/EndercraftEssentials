package me.limebyte.endercraftessentials;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EndercraftEssentials extends JavaPlugin {

    private static EndercraftEssentials instance;
    private static Logger logger;

    private static final String[] pranked = {};

    @Override
    public void onEnable() {
        instance = this;
        logger = this.getLogger();

        PluginManager pm = this.getServer().getPluginManager();

        Nameplates.init(this, pm);

        pm.registerEvents(new EventListener(), this);
        getCommand("formation").setExecutor(new FormationCommand());
        this.log().info("Enabled!");
    }

    @Override
    public void onDisable() {
        this.log().info("Disabled.");
    }

    public static EndercraftEssentials getInstance() {
        return instance;
    }

    public Logger log() {
        return logger;
    }

    public static String[] getPrankedNames() {
        return pranked;
    }

}
