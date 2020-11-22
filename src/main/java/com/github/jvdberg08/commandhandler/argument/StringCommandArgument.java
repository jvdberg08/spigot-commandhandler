package com.github.jvdberg08.commandhandler.argument;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

public class StringCommandArgument implements CommandArgument<String> {

    private final String[] validArguments;

    public StringCommandArgument(String... validArguments) {
        this.validArguments = validArguments;
    }

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return ArrayUtils.isEmpty(validArguments) || Arrays.stream(validArguments).anyMatch(commandArgument::equalsIgnoreCase);
    }

    @Override
    public String getArgument(String commandArgument, Object[] previousArguments) {
        return commandArgument;
    }

}
