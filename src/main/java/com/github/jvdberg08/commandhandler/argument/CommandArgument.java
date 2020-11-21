package com.github.jvdberg08.commandhandler.argument;

public interface CommandArgument<T> {

    boolean checkArgument(String commandArgument, Object[] previousArguments);

    T getArgument(String commandArgument, Object[] previousArguments);

}
