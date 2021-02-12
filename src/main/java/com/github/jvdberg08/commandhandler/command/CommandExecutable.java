package com.github.jvdberg08.commandhandler.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public interface CommandExecutable {

    boolean onPlayerCommand(Player player, List<Object> args, int syntaxUsed);

    boolean onConsoleCommand(CommandSender sender, List<Object> args, int syntaxUsed);

}
