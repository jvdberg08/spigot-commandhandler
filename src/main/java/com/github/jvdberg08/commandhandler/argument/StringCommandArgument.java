package com.github.jvdberg08.commandhandler.argument;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class StringCommandArgument implements CommandArgument<String> {

    private final boolean caseSensitive;
    private final String[] validArguments;

    public StringCommandArgument(boolean caseSensitive, String validArgument, String... validArguments) {
        this.caseSensitive = caseSensitive;

        List<String> list = new ArrayList<>();
        list.add(validArgument);
        list.addAll(Arrays.asList(validArguments));
        this.validArguments = list.toArray(new String[0]);
    }

    @Override
    public boolean checkArgument(String commandArgument, List<Object> previousArguments) {
        return Arrays.stream(validArguments).anyMatch(caseSensitive ? commandArgument::equals : commandArgument::equalsIgnoreCase);
    }

    @Override
    public String getArgument(String commandArgument, List<Object> previousArguments) {
        return commandArgument;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Arrays.stream(validArguments).filter(str -> caseSensitive
                ? str.startsWith(args[args.length - 1])
                : str.toLowerCase().startsWith((args[args.length - 1]).toLowerCase())).collect(Collectors.toList());
    }
}
