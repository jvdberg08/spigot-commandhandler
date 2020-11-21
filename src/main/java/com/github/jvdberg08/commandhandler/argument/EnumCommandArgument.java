package com.github.jvdberg08.commandhandler.argument;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EnumCommandArgument implements CommandArgument<Enum<?>> {

    private final Enum<?>[] validArgumentHolders;

    public EnumCommandArgument(Enum<?>... validArgumentHolders) {
        if(validArgumentHolders == null) {
            //TODO throw error
        }
        this.validArgumentHolders = validArgumentHolders;
    }

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return Arrays.stream(validArgumentHolders).map(Enum::toString).collect(Collectors.toList()).contains(commandArgument);
    }

    @Override
    public Enum<?> getArgument(String commandArgument, Object[] previousArguments) {
        return Arrays.stream(validArgumentHolders).filter(enumVariable -> enumVariable.toString().equalsIgnoreCase(commandArgument)).findFirst().get();
    }
}
