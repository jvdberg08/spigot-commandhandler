package com.github.jvdberg08.commandhandler.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SubCommand {

    abstract boolean onConsoleCommand(CommandSender sender, Object[] args, int syntaxUsed);

    abstract boolean onPlayerCommand(Player sender, Object[] args, int syntaxUsed);

}
