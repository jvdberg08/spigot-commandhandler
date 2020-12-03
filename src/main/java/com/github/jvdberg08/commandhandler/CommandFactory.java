package com.github.jvdberg08.commandhandler;

import com.github.jvdberg08.commandhandler.command.CommandNotDefinedException;
import com.github.jvdberg08.commandhandler.command.MainCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandFactory implements TabExecutor {

    private final Map<String, MainCommand> commands = new HashMap<>();

    public void registerCommand(MainCommand mainCommand) {
        commands.put(mainCommand.getName(), mainCommand);
        PluginCommand pluginCommand = Bukkit.getPluginCommand(mainCommand.getName());
        if (pluginCommand == null) {
            throw new CommandNotDefinedException("Command \"" + mainCommand.getName() + "\" is not defined!");
        }
        pluginCommand.setExecutor(this);
        pluginCommand.setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String s, String[] args) {

        if (!commands.containsKey(cmd.getName())) {
            return false;
        }

        MainCommand mainCommand = commands.get(cmd.getName());
        if (!mainCommand.onCommand(sender, args)) {
            //TODO call help command
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return null;
    }
}
