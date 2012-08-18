package me.limebyte.endercraftessentials;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;

/**
 * Represents the class which manages all events used by this JavaPlugin.
 */
public class EventListener implements Listener {

	/**
	 * Fired when Spoutcraft is enabled by the client.
	 * @param event The event fired
	 */
	@EventHandler
	public final void onSpoutcraftEnable(final SpoutCraftEnableEvent event) {
		SplashScreen splash = new SplashScreen(event.getPlayer());
		splash.attach("http://cdn.spout.org/img/logo/spout_328x145.png");
	}
	
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
