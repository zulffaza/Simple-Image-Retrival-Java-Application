package com.faza.example.simple.image.retrival.java.application.command.utils;

import com.faza.example.simple.image.retrival.java.application.command.executor.CommandExecutor;
import com.faza.example.simple.image.retrival.java.application.command.executor.implementation.CommandExecutorImpl;
import com.faza.example.simple.image.retrival.java.application.command.hsv.HsvCommand;
import com.faza.example.simple.image.retrival.java.application.command.hsv.implementation.HsvCommandImpl;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 5 June 2018
 */

public class InjectionUtils {

    public static CommandExecutor provideExecutor() {
        return CommandExecutorImpl.getInstance();
    }

    public static HsvCommand provideHsv() {
        return HsvCommandImpl.getInstance();
    }
}
