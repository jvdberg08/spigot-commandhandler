package com.github.jvdberg08.commandhandler.argument;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerCommandArgument implements CommandArgument<Player> {

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return Bukkit.getPlayer(commandArgument) != null;
    }

    @Override
    public Player getArgument(String commandArgument, Object[] previousArguments) {
        return Bukkit.getPlayer(commandArgument);
    }

    @Override
    public List<String> tabComplete(CommandSender sender) {
        List<String> names = new ArrayList<>(Bukkit.getOnlinePlayers().size());
        for (Player player : Bukkit.getOnlinePlayers()) {
            names.add(player.getName());
        }
        return names;
    }
}
