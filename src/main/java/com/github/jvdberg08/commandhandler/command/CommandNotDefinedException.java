package com.github.jvdberg08.commandhandler.command;

public class CommandNotDefinedException extends RuntimeException {

    public CommandNotDefinedException() {
        super("");
    }

    public CommandNotDefinedException(String message) {
        super(message);
    }

}
