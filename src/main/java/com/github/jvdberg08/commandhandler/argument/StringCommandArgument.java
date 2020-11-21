package com.github.jvdberg08.commandhandler.argument;

public class StringCommandArgument implements CommandArgument<String> {

    private final String validArgument;

    public StringCommandArgument(String validArgument) {
        this.validArgument = validArgument;
    }

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return commandArgument.equalsIgnoreCase(validArgument);
    }

    @Override
    public String getArgument(String commandArgument, Object[] previousArguments) {
        return commandArgument;
    }

}
