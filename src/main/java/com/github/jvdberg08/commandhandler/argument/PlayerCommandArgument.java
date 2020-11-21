package com.github.jvdberg08.commandhandler.argument;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerCommandArgument implements CommandArgument<Player> {

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return Bukkit.getPlayer(commandArgument) != null;
    }

    @Override
    public Player getArgument(String commandArgument, Object[] previousArguments) {
        return Bukkit.getPlayer(commandArgument);
    }
}
