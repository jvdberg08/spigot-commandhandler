package com.github.jvdberg08.commandhandler.argument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCommandArgument implements CommandArgument<String> {

    private final String[] validArguments;

    public StringCommandArgument(String validArgument) {
        this.validArguments = new String[]{validArgument};
    }

    public StringCommandArgument(String validArgument, String... moreValidArguments) {
        List<String> validArgumentsList = new ArrayList<>();
        validArgumentsList.add(validArgument);
        validArgumentsList.addAll(Arrays.asList(moreValidArguments));
        this.validArguments = validArgumentsList.toArray(new String[0]);
    }

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return Arrays.stream(validArguments).map(String::toLowerCase).collect(Collectors.toList()).contains(commandArgument.toLowerCase());
    }

    @Override
    public String getArgument(String commandArgument, Object[] previousArguments) {
        return commandArgument;
    }

}
