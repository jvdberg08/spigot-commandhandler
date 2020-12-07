package com.github.jvdberg08.commandhandler.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface HelpExecutable {

    boolean onHelpPlayerCommand(Player player, String[] args);

    boolean onHelpConsoleCommand(CommandSender sender, String[] args);

}
