package com.github.jvdberg08.commandhandler.argument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumCommandArgument implements CommandArgument<Enum<?>> {

    private final Enum<?>[] validArgumentHolders;

    public EnumCommandArgument(Enum<?> validArgumentHolder) {
        this.validArgumentHolders = new Enum[]{validArgumentHolder};
    }

    public EnumCommandArgument(Enum<?> validArgumentHolder, Enum<?>... moreValidArgumentHolders) {
        List<Enum<?>> validArgumentHoldersList = new ArrayList<>();
        validArgumentHoldersList.add(validArgumentHolder);
        validArgumentHoldersList.addAll(Arrays.asList(moreValidArgumentHolders));
        this.validArgumentHolders = validArgumentHoldersList.toArray(new Enum[0]);
    }

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        return Arrays.stream(validArgumentHolders).map(Enum::name).collect(Collectors.toList()).contains(commandArgument);
    }

    @Override
    public Enum<?> getArgument(String commandArgument, Object[] previousArguments) {
        return Arrays.stream(validArgumentHolders).filter(enumVariable -> enumVariable.name().equalsIgnoreCase(commandArgument)).collect(Collectors.toList()).get(0);
    }
}
