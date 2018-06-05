package com.faza.example.simple.image.retrival.java.application.command;

import java.util.Optional;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 5 June 2018
 */

public interface Command<REQUEST, RESPONSE> {

    Optional<RESPONSE> execute(REQUEST request) throws Exception;
}
