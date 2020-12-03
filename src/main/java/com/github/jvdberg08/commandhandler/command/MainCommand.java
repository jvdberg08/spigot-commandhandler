package com.github.jvdberg08.commandhandler.command;

import com.github.jvdberg08.commandhandler.CommandFactory;
import com.github.jvdberg08.commandhandler.argument.CommandArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class MainCommand extends SubCommand {

    private final String name;
    private final CommandFactory commandFactory;
    private final List<CommandSyntax> validSyntaxes = new ArrayList<>();

    public MainCommand(String name, CommandFactory commandFactory) {
        this.name = name;
        this.commandFactory = commandFactory;
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

            SubCommand subCommand = null;
            if (arguments.size() > 0 && arguments.get(0) instanceof SubCommand) {
                subCommand = (SubCommand) arguments.get(0);
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
            if (subCommand == null) {
                if (sender instanceof Player) {
                    return onPlayerCommand((Player) sender, parsedArguments, syntaxUsed);
                }
                return onConsoleCommand(sender, parsedArguments, syntaxUsed);
            }


            if (sender instanceof Player) {
                return subCommand.onPlayerCommand((Player) sender, parsedArguments, syntaxUsed);
            }
            return subCommand.onConsoleCommand(sender, parsedArguments, syntaxUsed);
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
