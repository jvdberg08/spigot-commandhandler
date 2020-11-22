package com.github.jvdberg08.commandhandler.argument;

import java.util.Arrays;

public class StringCommandArgument implements CommandArgument<String> {

    private final String[] validArguments;

    public StringCommandArgument(String... validArguments) {
        if(validArguments.length == 0){
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

}
