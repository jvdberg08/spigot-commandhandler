package com.github.jvdberg08.commandhandler.command;

import com.github.jvdberg08.commandhandler.argument.CommandArgument;

import java.util.ArrayList;
import java.util.List;

public class CommandSyntax {

    private final String usage;
    private final List<CommandArgument<?>> commandArguments = new ArrayList<>();
    private boolean allowConsole = false;

    public CommandSyntax(String usage) {
        this.usage = usage;
    }

    public CommandSyntax allowConsole() {
        allowConsole = true;
        return this;
    }

    public CommandSyntax addArgument(CommandArgument<?> commandArgument) {
        commandArguments.add(commandArgument);
        return this;
    }

    public String getUsage() {
        return usage;
    }

    public List<CommandArgument<?>> getCommandArguments() {
        return commandArguments;
    }

    public boolean isConsoleAllowed() {
        return allowConsole;
    }
}
