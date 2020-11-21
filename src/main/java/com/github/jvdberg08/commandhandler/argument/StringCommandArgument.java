package com.github.jvdberg08.commandhandler.argument;

import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCommandArgument implements CommandArgument<String> {

    private final String[] validArguments;

    public StringCommandArgument(String... validArguments) {
        if(validArguments == null) {
            //TODO throw error
        }
        this.validArguments = Stream.of(validArguments).map(String::toLowerCase).toArray(String[]::new);
    }

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return ArrayUtils.contains(validArguments, commandArgument.toLowerCase());
    }

    @Override
    public String getArgument(String commandArgument, Object[] previousArguments) {
        return commandArgument;
    }

}
