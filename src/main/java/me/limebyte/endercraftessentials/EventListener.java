package me.limebyte.endercraftessentials;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.kitteh.tag.PlayerReceiveNameTagEvent;
import org.kitteh.tag.TagAPI;

public class EventListener implements Listener {

    private static final Material LIGHT_ITEM = Material.GLOWSTONE_DUST;
    private static final String REI_PREFIX = "&0&0";
    private static final String REI_SUFFIX = "&e&f";

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getItem().getType() == LIGHT_ITEM) {
                    Block block = event.getClickedBlock().getRelative(event.getBlockFace());
                    int lightLevel = block.getLightLevel();
                    event.getPlayer().sendMessage(ChatColor.GOLD + "The light level of the selected block is " + lightLevel + ".");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        setDisplayName(player);
        String welcome = ChatColor.DARK_PURPLE + "Welcome to Endercraft " + player.getDisplayName() + "!";
        String message = REI_PREFIX + "&2&3" + REI_SUFFIX;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message) + welcome);
        event.setJoinMessage(event.getJoinMessage().replaceAll(player.getName(), player.getDisplayName()));

        if (player.getName().equalsIgnoreCase("bg1345") || player.getName().equalsIgnoreCase("jkjoka")) {
            player.sendMessage("You have pranked");
            player.sendMessage("but are now outranked.");
            player.sendMessage("Blocks for code,");
            player.sendMessage("pranks echoed.");
            player.sendMessage("Not to be rude,");
            player.sendMessage("but I have called you a noob.");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(event.getQuitMessage().replaceAll(player.getName(), player.getDisplayName()));
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        event.setDeathMessage(event.getDeathMessage().replaceAll(player.getName(), player.getDisplayName()));
    }

    @EventHandler
    public void onNameplate(PlayerReceiveNameTagEvent event) {
        if (!event.isModified()) {
            String name = event.getNamedPlayer().getName();
            String displayName = event.getNamedPlayer().getDisplayName();

            if (name.equalsIgnoreCase("bj2864") || name.equalsIgnoreCase("bg1345") || name.equalsIgnoreCase("jkjoka")) {
                event.setTag(displayName);
            }
        }
    }

    private void setDisplayName(Player player) {
        String name = player.getName();

        if (name.equalsIgnoreCase("limebyte")) {
            rename(player, "LimeByte");
        } else if (name.equalsIgnoreCase("bj2864")) {
            rename(player, "BennyBoi");
        } else if (name.equalsIgnoreCase("bg1345")) {
            rename(player, "Noob 2");
        } else if (name.equalsIgnoreCase("tegdim")) {
            rename(player, "Tegdim");
        } else if (name.equalsIgnoreCase("jkjoka")) {
            rename(player, "Noob 1");
        }
    }

    private void rename(Player player, String name) {
        player.setDisplayName(name);
        player.setPlayerListName(name);
        TagAPI.refreshPlayer(player);
    }

}
