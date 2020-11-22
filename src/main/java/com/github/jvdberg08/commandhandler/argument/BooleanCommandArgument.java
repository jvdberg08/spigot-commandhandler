package com.github.jvdberg08.commandhandler.argument;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.CommandSender;

import java.util.List;

public class BooleanCommandArgument implements CommandArgument<Boolean> {

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return commandArgument.equalsIgnoreCase("true") || commandArgument.equalsIgnoreCase("false");
    }

    @Override
    public Boolean getArgument(String commandArgument, Object[] previousArguments) {
        return Boolean.valueOf(commandArgument);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return ImmutableList.of("true", "false");
    }
}
