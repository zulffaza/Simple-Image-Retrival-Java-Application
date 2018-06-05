package com.faza.example.simple.image.retrival.java.application.command.executor.implementation;

import com.faza.example.simple.image.retrival.java.application.command.Command;
import com.faza.example.simple.image.retrival.java.application.command.executor.CommandExecutor;

import java.util.Optional;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 5 June 2018
 */

public class CommandExecutorImpl implements CommandExecutor {

    private static CommandExecutorImpl sInstance;

    private CommandExecutorImpl() {
        // Default constructor
    }

    public static CommandExecutorImpl getInstance() {
        if (sInstance == null)
            sInstance = new CommandExecutorImpl();

        return sInstance;
    }

    @Override
    public <REQUEST, RESPONSE> Optional<RESPONSE> doExecute(Command<REQUEST, RESPONSE> command, REQUEST request)
            throws Exception {
        return command.execute(request);
    }
}
