package com.github.jvdberg08.commandhandler.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface CommandExecutable {

    boolean onPlayerCommand(Player player, Object[] args, int syntaxUsed);

    boolean onConsoleCommand(CommandSender sender, Object[] args, int syntaxUsed);

}
