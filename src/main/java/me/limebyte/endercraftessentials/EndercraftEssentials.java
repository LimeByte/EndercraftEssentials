package me.limebyte.endercraftessentials;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Represents the main class for EndercraftEssentials.
 */
public class EndercraftEssentials extends JavaPlugin {

	/**
	 * The current running EndercraftEssentials Object instance.
	 */
	private static EndercraftEssentials instance;
	
	/**
	 * Cast when this JavaPlugin is enabled by the server instance.
	 */
	@Override
	public final void onEnable() {
		// Set instance
		instance = this;
		
		// Register Events
		this.getServer().getPluginManager().registerEvents(new EventListener(), this);
	}
	
	/**
	 * Gets the current running EndercraftEssentials Object instance.
	 * @return The current instance
	 */
	public final EndercraftEssentials getInstance() {
		return instance;
	}
	
}
