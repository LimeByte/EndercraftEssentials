package me.limebyte.endercraftessentials;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

/**
 * Represents the class which manages all events used by this JavaPlugin.
 */
public class EventListener implements Listener {
	
	/**
	 * 
	 */
	@EventHandler
	public final void onPlayerKick(final PlayerKickEvent event) {
		if (event.getPlayer().getName().equalsIgnoreCase("limebyte")) {
			event.setCancelled(true);
		}
	}
	
}
