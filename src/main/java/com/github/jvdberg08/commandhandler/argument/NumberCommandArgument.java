package com.github.jvdberg08.commandhandler.argument;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.CommandSender;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

public class NumberCommandArgument implements CommandArgument<Number> {

    @Override
    public boolean checkArgument(String commandArgument, List<Object> previousArguments) {
        try {
            NumberFormat.getInstance().parse(commandArgument);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public Number getArgument(String commandArgument, List<Object> previousArguments) {
        try {
            return NumberFormat.getInstance().parse(commandArgument);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return ImmutableList.of("1", "4", "8", "16", "32", "64");
    }
}
