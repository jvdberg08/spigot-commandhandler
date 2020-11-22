package com.github.jvdberg08.commandhandler.argument;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

public class EnumCommandArgument implements CommandArgument<Enum<?>> {

    private final Enum<?>[] validArgumentHolders;

    public EnumCommandArgument(Enum<?>... validArgumentHolders) {
        this.validArgumentHolders = validArgumentHolders;
    }

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return ArrayUtils.isEmpty(validArgumentHolders) || Arrays.stream(validArgumentHolders).map(Enum::toString).anyMatch(commandArgument::equalsIgnoreCase);
    }

    @Override
    public Enum<?> getArgument(String commandArgument, Object[] previousArguments) {
        return Arrays.stream(validArgumentHolders).filter(enumVariable -> enumVariable.toString().equalsIgnoreCase(commandArgument)).findFirst().orElse(null);
    }
}
