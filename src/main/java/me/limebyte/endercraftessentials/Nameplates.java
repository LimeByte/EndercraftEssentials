package me.limebyte.endercraftessentials;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.UnknownDependencyException;

public class Nameplates {
    public static boolean init(Plugin plugin) {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        if (pm.getPlugin("TagAPI") == null) {
            plugin.getLogger().log(Level.WARNING, "TagAPI not found.  Installing...");
            try {
                install(plugin, pm);
            } catch (Exception e) {
                plugin.getLogger().log(Level.SEVERE, "Failed to install TagAPI.  Disabling...");
                return false;
            } finally {
                plugin.getLogger().log(Level.INFO, "TagAPI installed successfully.");
            }
        }
        return true;
    }

    private static void install(Plugin plugin, PluginManager pm) throws UnknownDependencyException, InvalidPluginException, InvalidDescriptionException {
        File tagAPI = new File(plugin.getDataFolder().getParent(), "TagAPI.jar");
        copyResource(plugin, "TagAPI.jar", tagAPI);
        load(tagAPI);
    }

    private static void load(File file) throws UnknownDependencyException, InvalidPluginException, InvalidDescriptionException {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.loadPlugin(file);
        pm.enablePlugin(pm.getPlugin("TagAPI"));
    }

    public static void copyResource(Plugin plugin, String resource, File file) {
        InputStream in = plugin.getResource(resource);
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
