package com.github.jvdberg08.commandhandler.argument;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).filter(name -> name.toLowerCase().startsWith(args[args.length - 1])).collect(Collectors.toList());
    }
}
