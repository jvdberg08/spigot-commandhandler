package com.github.jvdberg08.commandhandler.argument;

import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCommandArgument implements CommandArgument<String> {

    private final String[] validArguments;

    public StringCommandArgument(String... validArguments) {
        if (validArguments.length == 0) {
            throw new IllegalArgumentException("StringCommandArgument can not have no valid argument value.");
        }
        this.validArguments = validArguments;
    }

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return Arrays.stream(validArguments).anyMatch(commandArgument::equalsIgnoreCase);
    }

    @Override
    public String getArgument(String commandArgument, Object[] previousArguments) {
        return commandArgument;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Arrays.stream(validArguments).filter(str -> str.startsWith(args[args.length - 1])).collect(Collectors.toList());
    }

}
