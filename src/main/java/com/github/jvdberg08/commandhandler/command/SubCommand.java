package com.github.jvdberg08.commandhandler.command;

import com.github.jvdberg08.commandhandler.argument.StringCommandArgument;

public abstract class SubCommand extends StringCommandArgument implements CommandExecutable {

    public SubCommand(String validArgument, String... validArguments) {
        super(validArgument, validArguments);
    }
}
