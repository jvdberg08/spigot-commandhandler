package com.github.jvdberg08.commandhandler.argument;

import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumCommandArgument implements CommandArgument<Enum<?>> {

    private final Enum<?>[] validArgumentHolders;

    public EnumCommandArgument(Enum<?> validArgumentHolder, Enum<?>... validArgumentHolders) {
        List<Enum<?>> list = Arrays.asList(validArgumentHolders);
        list.add(validArgumentHolder);
        this.validArgumentHolders = list.toArray(new Enum[0]);
    }

    @Override
    public boolean checkArgument(String commandArgument, List<Object> previousArguments) {
        return Arrays.stream(validArgumentHolders).map(Enum::toString).anyMatch(commandArgument::equalsIgnoreCase);
    }

    @Override
    public Enum<?> getArgument(String commandArgument, List<Object> previousArguments) {
        return Arrays.stream(validArgumentHolders).filter(enumVariable -> enumVariable.toString().equalsIgnoreCase(commandArgument)).findFirst().orElse(null);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Arrays.stream(validArgumentHolders).map(Enum::toString).filter(s -> s.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).collect(Collectors.toList());
    }
}
