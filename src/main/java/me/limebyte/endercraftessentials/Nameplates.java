package me.limebyte.endercraftessentials;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.UnknownDependencyException;
import org.kitteh.tag.TagAPI;

public class Nameplates {
    private static boolean enabled = false;

    public static void init(EndercraftEssentials plugin, PluginManager pm) {
        if (pm.getPlugin("TagAPI") == null) {
            plugin.log().warning("TagAPI not found.  Installing...");
            try {
                install(plugin, pm);
            } catch (Exception e) {
                plugin.log().warning("Failed to install TagAPI.");
                enabled = false;
                return;
            }
            plugin.log().info("TagAPI installed successfully.");
        }
        enabled = true;
    }

    private static void install(EndercraftEssentials plugin, PluginManager pm) throws UnknownDependencyException, InvalidPluginException, InvalidDescriptionException {
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

    public static void refresh(Player player) {
        if (!enabled) return;
        try {
            TagAPI.refreshPlayer(player);
        } catch (Exception e) {
        }
    }

}
