package com.github.jvdberg08.commandhandler.argument;

import java.util.Arrays;

public class EnumCommandArgument implements CommandArgument<Enum<?>> {

    private final Enum<?>[] validArgumentHolders;

    public EnumCommandArgument(Enum<?>... validArgumentHolders) {
        if (validArgumentHolders.length == 0) {
            throw new IllegalArgumentException("EnumCommandArgument can not have no valid argument value.");
        }
        this.validArgumentHolders = validArgumentHolders;
    }

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return Arrays.stream(validArgumentHolders).map(Enum::toString).anyMatch(commandArgument::equalsIgnoreCase);
    }

    @Override
    public Enum<?> getArgument(String commandArgument, Object[] previousArguments) {
        return Arrays.stream(validArgumentHolders).filter(enumVariable -> enumVariable.toString().equalsIgnoreCase(commandArgument)).findFirst().orElse(null);
    }
}
