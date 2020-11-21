package com.github.jvdberg08.commandhandler.argument;

public class BooleanCommandArgument implements CommandArgument<Boolean> {

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return commandArgument.equalsIgnoreCase("true") || commandArgument.equalsIgnoreCase("false");
    }

    @Override
    public Boolean getArgument(String commandArgument, Object[] previousArguments) {
        return Boolean.valueOf(commandArgument);
    }
}
