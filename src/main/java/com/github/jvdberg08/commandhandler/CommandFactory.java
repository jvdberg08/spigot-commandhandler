package com.github.jvdberg08.commandhandler;

import com.github.jvdberg08.commandhandler.argument.CommandArgument;
import com.github.jvdberg08.commandhandler.command.Command;
import com.github.jvdberg08.commandhandler.command.CommandNotDefinedException;
import com.github.jvdberg08.commandhandler.command.CommandSyntax;
import com.github.jvdberg08.commandhandler.command.HelpCommand;
import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class CommandFactory implements TabExecutor {

    private final String main;
    private final Map<String, Command> commands = new HashMap<>();

    public CommandFactory(String main) {

        this.main = main;
        PluginCommand pluginCommand = Bukkit.getPluginCommand(main);
        if (pluginCommand == null) {
            throw new CommandNotDefinedException("Command \"" + main + "\" is not defined.");
        }
        pluginCommand.setExecutor(this);
        pluginCommand.setTabCompleter(this);
        Bukkit.getLogger().info("CommandFactory \"" + main + "\" has been created!");

        registerCommand(new HelpCommand("help", this));
    }

    public void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String s, String[] args) {

        if (!cmd.getName().equalsIgnoreCase(main)) return false;

        if (args.length == 0) {
            showHelp(sender, args);
            return false;
        }

        String commandName = args[0];
        Command command = commands.get(commandName);
        if (command == null) {
            showHelp(sender, args);
            return false;
        }

        args = Arrays.copyOfRange(args, 1, args.length);

        syntaxloop:
        for (CommandSyntax commandSyntax : command.getValidSyntaxes()) {

            List<CommandArgument<?>> commandArguments = commandSyntax.getCommandArguments();
            if (args.length != commandArguments.size()) {
                continue;
            }

            if (!commandSyntax.isConsoleAllowed() && !(sender instanceof Player)) {
                continue;
            }

            Object[] parsedArguments = new Object[commandArguments.size()];
            for (int i = 0; i < commandArguments.size(); i++) {
                CommandArgument<?> commandArgument = commandArguments.get(i);

                if (!commandArgument.checkArgument(args[i], parsedArguments)) {
                    continue syntaxloop;
                }

                parsedArguments[i] = commandArgument.getArgument(args[i], parsedArguments);
            }

            if (sender instanceof Player) {
                command.onPlayerCommand((Player) sender, parsedArguments, command.getValidSyntaxes().indexOf(commandSyntax));
            } else {
                command.onConsoleCommand(sender, parsedArguments, command.getValidSyntaxes().indexOf(commandSyntax));
            }
            return true;
        }

        showHelp(sender, commandName, args);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command cmd, String s, String[] args) {
        if (!cmd.getName().equalsIgnoreCase(main)) return ImmutableList.of();

        if (args.length == 1) {
            return getCommandsStartingWith(args[0]);
        }

        String commandName = args[0];
        Command command = commands.get(commandName);
        if (command == null) {
            return new ArrayList<>();
        }

        args = Arrays.copyOfRange(args, 1, args.length);
        for (CommandSyntax commandSyntax : command.getValidSyntaxes()) {

            List<CommandArgument<?>> commandArguments = commandSyntax.getCommandArguments();
            if (args.length > commandArguments.size()) {
                continue;
            }

            if (!commandSyntax.isConsoleAllowed() && !(sender instanceof Player)) {
                continue;
            }

            return commandArguments.get(args.length - 1).tabComplete(sender, args);
        }
        return ImmutableList.of();
    }

    public void showHelp(CommandSender sender, String[] args) {
        Command helpCommand = commands.get("help");
        if (helpCommand == null) {
            sender.sendMessage(ChatColor.RED + "Invalid args!");
        } else {
            helpCommand.onConsoleCommand(sender, args, -1);
        }
    }

    public void showHelp(CommandSender sender, String subCommandName, String[] args) {
        String[] newArray = new String[args.length + 1];
        newArray[0] = subCommandName;
        System.arraycopy(args, 0, newArray, 1, args.length);
        showHelp(sender, newArray);
    }

    public String getMain() {
        return main;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    private List<String> getCommandsStartingWith(String string) {
        return commands.keySet().stream().filter(name -> name.toLowerCase().startsWith(string.toLowerCase())).collect(Collectors.toList());
    }
}
