package com.github.jvdberg08.commandhandler.argument;

import java.text.NumberFormat;
import java.text.ParseException;

public class NumberCommandArgument implements CommandArgument<Number> {

    @Override
    public boolean checkArgument(String commandArgument, Object[] previousArguments) {
        try {
            NumberFormat.getInstance().parse(commandArgument);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public Number getArgument(String commandArgument, Object[] previousArguments) {
        try {
            return NumberFormat.getInstance().parse(commandArgument);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
