package com.github.jvdberg08.commandhandler.argument;

import java.util.EnumSet;

public class FixedEnumCommandArgument extends FixedCommandArgument {

    public <E extends Enum<E>> FixedEnumCommandArgument(EnumSet<E> enumSet) {
        super(enumSet.toArray(new String[enumSet.size()]));
    }

    public <E extends Enum<E>> FixedEnumCommandArgument(E... enumArray) {
        super(enumArrayToStringArray(enumArray));
    }

    private static <E extends Enum<E>> String[] enumArrayToStringArray(E... enumArray) {
        if(enumArray == null) return new String[] {};
        String[] strings = new String[enumArray.length];
        for(int i=0; i<enumArray.length; i++) {
            strings[i] = enumArray[i].toString();
        }
        return strings;
    }
}

