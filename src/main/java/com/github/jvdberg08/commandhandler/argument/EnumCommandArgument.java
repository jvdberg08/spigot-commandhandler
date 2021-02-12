package com.github.jvdberg08.commandhandler.argument;

import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumCommandArgument implements CommandArgument<Enum<?>> {

    private final boolean caseSensitive;
    private final Enum<?>[] validArgumentHolders;

    public EnumCommandArgument(boolean caseSensitive, Enum<?> validArgumentHolder, Enum<?>... validArgumentHolders) {
        this.caseSensitive = caseSensitive;

        List<Enum<?>> list = Arrays.asList(validArgumentHolders);
        list.add(validArgumentHolder);
        this.validArgumentHolders = list.toArray(new Enum[0]);
    }

    @Override
    public boolean checkArgument(String commandArgument, List<Object> previousArguments) {
        return Arrays.stream(validArgumentHolders).map(Enum::toString).anyMatch(caseSensitive
                ? commandArgument::equals
                : commandArgument::equalsIgnoreCase);
    }

    @Override
    public Enum<?> getArgument(String commandArgument, List<Object> previousArguments) {
        return Arrays.stream(validArgumentHolders).filter(enumVariable -> caseSensitive
                ? enumVariable.toString().equals(commandArgument)
                : enumVariable.toString().equalsIgnoreCase(commandArgument)).findFirst().orElse(null);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Arrays.stream(validArgumentHolders).map(Enum::toString).filter(s -> caseSensitive
                ? s.startsWith(args[args.length - 1])
                : s.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).collect(Collectors.toList());
    }
}
