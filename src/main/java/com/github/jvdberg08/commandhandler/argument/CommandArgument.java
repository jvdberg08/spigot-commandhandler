package com.github.jvdberg08.commandhandler.argument;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface CommandArgument<T> {

    boolean checkArgument(String commandArgument, List<Object> previousArguments);

    T getArgument(String commandArgument, List<Object> previousArguments);

    List<String> tabComplete(CommandSender sender, String[] args);

}
