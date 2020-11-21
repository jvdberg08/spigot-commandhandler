package com.github.jvdberg08.commandhandler.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    private final String name;
    private final List<CommandSyntax> validSyntaxes = new ArrayList<>();

    public Command(String name) {
        this.name = name;
    }

    public abstract void onPlayerCommand(Player player, Object[] args, int syntaxUsed);

    public abstract void onConsoleCommand(CommandSender sender, Object[] args, int syntaxUsed);

    public CommandSyntax addCommandSyntax(String usage) {
        CommandSyntax commandSyntax = new CommandSyntax(usage);
        validSyntaxes.add(commandSyntax);
        return commandSyntax;
    }

    public String getName() {
        return name;
    }

    public List<CommandSyntax> getValidSyntaxes() {
        return validSyntaxes;
    }
}
