package me.limebyte.endercraftessentials;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EndercraftEssentials extends JavaPlugin {

    private static EndercraftEssentials instance;
    private static Logger logger;

    private static final long SAVE_PERIOD = 30 * 60 * 20;

    @Override
    public void onEnable() {
        instance = this;
        logger = this.getLogger();

        PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvents(new EventListener(), this);
        getCommand("formation").setExecutor(new FormationCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        this.log().info("Enabled!");

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                getServer().broadcastMessage("What up!  I'm saving da worlds!");
                for (World world : Bukkit.getWorlds()) {
                    world.save();
                }
            }
        }, SAVE_PERIOD, SAVE_PERIOD);
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
