package com.github.jvdberg08.commandhandler.command;

import com.github.jvdberg08.commandhandler.CommandFactory;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand extends Command {

    private final CommandFactory commandFactory;

    public HelpCommand(String name, CommandFactory commandFactory) {
        super(name);
        this.commandFactory = commandFactory;
    }

    @Override
    public void onPlayerCommand(Player player, Object[] args, int syntaxUsed) {
        onConsoleCommand(player, args, syntaxUsed);
    }

    @Override
    public void onConsoleCommand(CommandSender sender, Object[] args, int syntaxUsed) {

        sender.sendMessage(ChatColor.GOLD + "Help: /" + commandFactory.getMain());

        if (args.length == 0 || (args.length == 1 && args[0].equals("help"))) {
            if (commandFactory.getCommands().size() == 0) {
                sender.sendMessage(ChatColor.RED + "No commands found!");
                return;
            }

            for (Command command : commandFactory.getCommands().values()) {
                showHelp(sender, command);
            }
            return;
        }

        String subCommandName = ((String) args[0]).equalsIgnoreCase("help") ? (String) args[1] : (String) args[0];
        Command specifiedCommand = commandFactory.getCommands().get(subCommandName);
        if (specifiedCommand == null) {
            for (Command command : commandFactory.getCommands().values()) {
                showHelp(sender, command);
            }
        } else {
            showHelp(sender, specifiedCommand);
        }
    }

    public void showHelp(CommandSender sender, Command command) {
        sender.sendMessage(ChatColor.YELLOW + "/" + commandFactory.getMain() + " " + command.getName());
        for (CommandSyntax syntax : command.getValidSyntaxes()) {
            if (!syntax.getUsage().equals("")) {
                boolean bool = !syntax.isConsoleAllowed() && !(sender instanceof Player);
                sender.sendMessage("   - " + syntax.getUsage() + (bool ? ChatColor.RED + " CONSOLE" : ""));
            }
        }
    }
}
