package com.github.jvdberg08.commandhandler.command;

import com.github.jvdberg08.commandhandler.CommandFactory;
import com.github.jvdberg08.commandhandler.argument.CommandArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class MainCommand implements CommandExecutable {

    private final String name;
    private final CommandFactory commandFactory;
    private final List<CommandSyntax> validSyntaxes = new ArrayList<>();

    public MainCommand(String name, CommandFactory commandFactory) {
        this.name = name;
        this.commandFactory = commandFactory;
    }

    public CommandSyntax addCommandSyntax(String usage) {
        CommandSyntax syntax = new CommandSyntax(usage);
        validSyntaxes.add(syntax);
        return syntax;
    }

    public boolean onCommand(CommandSender sender, String[] args) {

        syntaxloop:
        for (CommandSyntax syntax : validSyntaxes) {
            List<CommandArgument<?>> arguments = syntax.getCommandArguments();
            if (arguments.size() != args.length) {
                continue;
            }
            if (!syntax.isConsoleAllowed() && !(sender instanceof Player)) {
                continue;
            }

            CommandExecutable commandExecutable;
            if (arguments.size() > 0 && arguments.get(0) instanceof SubCommand) {
                commandExecutable = (SubCommand) arguments.get(0);
            } else {
                commandExecutable = this;
            }

            Object[] parsedArguments = new Object[arguments.size()];
            for (int i = 0; i < arguments.size(); i++) {
                CommandArgument<?> commandArgument = arguments.get(i);

                if (!commandArgument.checkArgument(args[i], parsedArguments)) {
                    continue syntaxloop;
                }

                parsedArguments[i] = commandArgument.getArgument(args[i], parsedArguments);
            }

            int syntaxUsed = validSyntaxes.indexOf(syntax);
            if (sender instanceof Player) {
                return commandExecutable.onPlayerCommand((Player) sender, parsedArguments, syntaxUsed);
            }
            return commandExecutable.onConsoleCommand(sender, parsedArguments, syntaxUsed);
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

    public List<CommandSyntax> getValidSyntaxes() {
        return validSyntaxes;
    }
}
