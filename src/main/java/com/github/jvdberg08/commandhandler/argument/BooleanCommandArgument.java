package com.github.jvdberg08.commandhandler.argument;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BooleanCommandArgument implements CommandArgument<Boolean> {

    private final Map<String, Boolean> validArguments;

    public BooleanCommandArgument(Map<String, Boolean> validArguments) {
        this.validArguments = validArguments;
    }

    @Override
    public boolean checkArgument(String commandArgument, List<Object> previousArguments) {
        return validArguments.containsKey(commandArgument);
    }

    @Override
    public Boolean getArgument(String commandArgument, List<Object> previousArguments) {
        return validArguments.get(commandArgument);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>(validArguments.keySet());
    }
}
