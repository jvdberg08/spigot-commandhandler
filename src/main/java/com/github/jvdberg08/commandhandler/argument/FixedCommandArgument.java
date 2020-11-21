package com.github.jvdberg08.commandhandler.argument;

import org.apache.commons.lang.ArrayUtils;

public class FixedCommandArgument implements CommandArgument<String> {

    private final String[] fixed;

    public FixedCommandArgument(String[] fixed) {
        this.fixed = fixed;
    }

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return ArrayUtils.contains(fixed, commandArgument);
    }

    @Override
    public String getArgument(String commandArgument, Object[] previousArguments) {
        return commandArgument;
    }

}
