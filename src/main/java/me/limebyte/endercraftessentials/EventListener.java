package me.limebyte.endercraftessentials;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {

    private static final Material LIGHT_LEVEL_ITEM = Material.GLOWSTONE_DUST;
    private static final Material HUNGER_INFO_ITEM = Material.POISONOUS_POTATO;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getItem().getType() == LIGHT_LEVEL_ITEM) {
                    Block block = event.getClickedBlock().getRelative(event.getBlockFace());
                    int lightLevel = block.getLightLevel();
                    event.getPlayer().sendMessage(ChatColor.GOLD + "The light level of the selected block is " + lightLevel + ".");
                }
            }
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null) {
                if (event.getItem().getType() == HUNGER_INFO_ITEM) {
                    Player player = event.getPlayer();
                    String title = ChatColor.GOLD + "   --- " + ChatColor.ITALIC + "Hunger Info" + ChatColor.RESET + ChatColor.GOLD + " ---   ";

                    player.sendMessage(title);
                    player.sendMessage(ChatColor.WHITE + "FoodLevel: " + player.getFoodLevel());
                    player.sendMessage(ChatColor.WHITE + "Saturation: " + player.getSaturation());
                    player.sendMessage(ChatColor.WHITE + "Exhaustion: " + player.getExhaustion());
                    player.sendMessage(ChatColor.GOLD + "   -------------------   ");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        setDisplayName(player);

        player.sendMessage(ChatColor.GOLD + "Welcome to Endercraft " + player.getDisplayName() + "!");
        event.setJoinMessage(event.getJoinMessage().replaceAll(player.getName(), player.getDisplayName()));
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
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setFormat(ChatColor.GREEN + event.getPlayer().getDisplayName() + ": " + event.getMessage());
    }

    private void setDisplayName(Player player) {
        String name = player.getName();

        if (name.equalsIgnoreCase("limebyte")) {
            rename(player, "LimeByte");
        } else if (name.equalsIgnoreCase("bj2864")) {
            rename(player, "BennyBoi");
        } else if (name.equalsIgnoreCase("bg1345")) {
            rename(player, "Ashpof");
        } else if (name.equalsIgnoreCase("tegdim")) {
            rename(player, "Tegdim");
        } else if (name.equalsIgnoreCase("jerazz")) {
            rename(player, "Jew");
        }
    }

    private void rename(Player player, String name) {
        player.setDisplayName(name);
        setPlayerListName(player, name);
    }

    private void setPlayerListName(Player player, String name) {
        try {
            player.setPlayerListName(name);
        } catch (IllegalArgumentException e) {
            try {
                String number = String.valueOf(System.currentTimeMillis() % 9);

                if (16 - name.length() < 3) {
                    player.setPlayerListName(name.substring(0, 16 - 3) + " " + number);
                } else {
                    player.setPlayerListName(name + " " + number);
                }
            } catch (IllegalArgumentException e1) {
                setPlayerListName(player, name);
            }
        }
    }

}
