package com.github.jvdberg08.commandhandler;

import com.github.jvdberg08.commandhandler.argument.CommandArgument;
import com.github.jvdberg08.commandhandler.command.CommandNotDefinedException;
import com.github.jvdberg08.commandhandler.command.CommandSyntax;
import com.github.jvdberg08.commandhandler.command.HelpExecutable;
import com.github.jvdberg08.commandhandler.command.MainCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandFactory implements TabExecutor {

    private final Map<String, MainCommand> commands = new HashMap<>();
    private HelpExecutable helpExecutable;

    public void registerCommand(MainCommand mainCommand) {
        commands.put(mainCommand.getName(), mainCommand);
        PluginCommand pluginCommand = Bukkit.getPluginCommand(mainCommand.getName());
        if (pluginCommand == null) {
            throw new CommandNotDefinedException("Command \"" + mainCommand.getName() + "\" is not defined!");
        }
        pluginCommand.setExecutor(this);
        pluginCommand.setTabCompleter(this);
    }

    public void registerHelpCommand(HelpExecutable helpExecutable) {
        this.helpExecutable = helpExecutable;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!commands.containsKey(cmd.getName())) {
            return false;
        }

        MainCommand mainCommand = commands.get(cmd.getName());
        boolean executedCorrectly = mainCommand.onCommand(sender, args);

        if (!executedCorrectly) {
            if (helpExecutable == null) {
                sender.sendMessage(ChatColor.RED + "Invalid usage!");
                return false;
            }
            if (sender instanceof Player) {
                return helpExecutable.onHelpPlayerCommand((Player) sender, args);
            } else {
                return helpExecutable.onHelpConsoleCommand(sender, args);
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {

        if (!commands.containsKey(cmd.getName())) {
            return new ArrayList<>();
        }

        MainCommand mainCommand = commands.get(cmd.getName());
        List<String> toComplete = new ArrayList<>();
        for (CommandSyntax syntax : mainCommand.getValidSyntaxes()) {

            List<CommandArgument<?>> arguments = syntax.getCommandArguments();
            if (args.length > arguments.size()) {
                continue;
            }
            if (!syntax.isConsoleAllowed() && !(sender instanceof Player)) {
                continue;
            }

            toComplete.addAll(arguments.get(args.length - 1).tabComplete(sender, args));
        }
        return toComplete;
    }

    public Map<String, MainCommand> getCommands() {
        return commands;
    }
}
