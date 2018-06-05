package com.faza.example.simple.image.retrival.java.application.command.utils;

import com.faza.example.simple.image.retrival.java.application.command.executor.implementation.CommandExecutorImpl;
import com.faza.example.simple.image.retrival.java.application.command.hsv.implementation.HsvCommandImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 5 June 2018
 */

public class InjectionUtilsTest {

    @Test
    public void provideExecutor_success() {
        assertEquals(CommandExecutorImpl.getInstance(), InjectionUtils.provideExecutor());
    }

    @Test
    public void provideHsv_success() {
        assertEquals(HsvCommandImpl.getInstance(), InjectionUtils.provideHsv());
    }
}