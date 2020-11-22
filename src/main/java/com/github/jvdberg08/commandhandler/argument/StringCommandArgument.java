package com.github.jvdberg08.commandhandler.argument;

import org.bukkit.command.CommandSender;

import java.util.List;

public class StringCommandArgument implements CommandArgument<String> {

    private final String validArgument;

    public StringCommandArgument(String validArgument) {
        this.validArgument = validArgument;
    }

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return commandArgument.equalsIgnoreCase(validArgument);
    }

    @Override
    public String getArgument(String commandArgument, Object[] previousArguments) {
        return commandArgument;
    }

    @Override
    public List<String> tabComplete(CommandSender sender) {
        return null;
    }
}
