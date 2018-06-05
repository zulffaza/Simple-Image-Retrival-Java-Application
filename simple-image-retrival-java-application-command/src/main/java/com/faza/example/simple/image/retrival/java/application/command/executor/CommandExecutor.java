package com.faza.example.simple.image.retrival.java.application.command.executor;

import com.faza.example.simple.image.retrival.java.application.command.Command;

import java.util.Optional;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 5 June 2018
 */

public interface CommandExecutor {

    <REQUEST, RESPONSE> Optional<RESPONSE> doExecute(Command<REQUEST, RESPONSE> command, REQUEST request)
            throws Exception;
}
