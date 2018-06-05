package com.faza.example.simple.image.retrival.java.application.command.hsv.implementation;

import com.faza.example.simple.image.retrival.java.application.command.hsv.HsvCommand;
import com.faza.example.simple.image.retrival.java.application.command.model.request.HsvRequest;
import com.faza.example.simple.image.retrival.java.application.command.model.response.HsvResponse;
import com.faza.example.simple.image.retrival.java.application.command.utils.InjectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 5 June 2018
 */

public class HsvCommandImplTest {

    private HsvCommand hsvCommand;

    @Before
    public void setUp() {
        hsvCommand = InjectionUtils.provideHsv();
    }

    @Test
    public void execute_success() throws Exception {
        String filePath = "..\\simple-image-retrival-java-application-command\\src\\test\\resources\\test.png";

        HsvRequest hsvRequest = HsvRequest.builder()
                .filePath(filePath)
                .build();

        HsvResponse expectedHsvResponse = HsvResponse.builder()
                .red(145)
                .yellow(154)
                .green(603)
                .cyan(107)
                .blue(1)
                .magenta(14)
                .build();

        Optional<HsvResponse> hsvResponse = hsvCommand.execute(hsvRequest);

        assertNotNull(hsvResponse);
        assertNotNull(hsvResponse.orElse(null));
        assertEquals(expectedHsvResponse, hsvResponse.get());
    }

    @Test(expected = FileNotFoundException.class)
    public void execute_failed_fileNotFound() throws Exception {
        HsvRequest hsvRequest = HsvRequest.builder()
                .filePath("")
                .build();

        hsvCommand.execute(hsvRequest);
    }

    @Test(expected = NullPointerException.class)
    public void execute_failed_filePathIsNull() throws Exception {
        HsvRequest hsvRequest = HsvRequest.builder()
                .build();

        hsvCommand.execute(hsvRequest);
    }
}