package me.limebyte.endercraftessentials;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class EndercraftEssentials extends JavaPlugin {

    private static EndercraftEssentials instance;
    private static Logger logger;

    @Override
    public void onEnable() {
        instance = this;
        logger = this.getLogger();

        this.getServer().getPluginManager().registerEvents(new EventListener(), this);
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

}
