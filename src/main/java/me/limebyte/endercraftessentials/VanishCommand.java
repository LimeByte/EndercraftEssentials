package me.limebyte.endercraftessentials;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    private List<String> invisible = new ArrayList<String>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
        } else {
            Player player = (Player) sender;
            if (invisible.contains(player.getName())) {
                show(player);
                player.sendMessage("You have become visible.");
            } else {
                hide(player);
                player.sendMessage("Poof!.");
            }
        }
        return true;
    }

    private void hide(Player player) {
        invisible.add(player.getName());
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(player);
        }
    }

    private void show(Player player) {
        invisible.remove(player.getName());
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.showPlayer(player);
        }
    }
}
