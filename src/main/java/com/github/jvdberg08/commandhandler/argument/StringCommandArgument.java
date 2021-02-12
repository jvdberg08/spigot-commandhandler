package com.github.jvdberg08.commandhandler.argument;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCommandArgument implements CommandArgument<String> {

    private final String[] validArguments;

    public StringCommandArgument(String validArgument, String... validArguments) {
        List<String> list = new ArrayList<>();
        list.add(validArgument);
        list.addAll(Arrays.asList(validArguments));
        this.validArguments = list.toArray(new String[0]);
    }

    @Override
    public boolean checkArgument(String commandArgument, List<Object> previousArguments) {
        return Arrays.stream(validArguments).anyMatch(commandArgument::equalsIgnoreCase);
    }

    @Override
    public String getArgument(String commandArgument, List<Object> previousArguments) {
        return commandArgument;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Arrays.stream(validArguments).filter(str -> str.startsWith(args[args.length - 1])).collect(Collectors.toList());
    }

}
