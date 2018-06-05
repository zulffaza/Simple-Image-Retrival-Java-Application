package com.faza.example.simple.image.retrival.java.application.main;

import com.faza.example.simple.image.retrival.java.application.command.executor.CommandExecutor;
import com.faza.example.simple.image.retrival.java.application.command.hsv.HsvCommand;
import com.faza.example.simple.image.retrival.java.application.command.model.request.HsvRequest;
import com.faza.example.simple.image.retrival.java.application.command.model.response.HsvResponse;
import com.faza.example.simple.image.retrival.java.application.command.utils.InjectionUtils;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 5 June 2018
 */

public class Application {

    private static final Integer FIRST_INDEX = 0;

    private static final Integer INITIAL_VALUE = 0;

    private static Application sInstance;

    private Application() {
        // Default constructor
    }

    private static Application getInstance() {
        if (sInstance == null)
            sInstance = new Application();

        return sInstance;
    }

    public static void main(String[] args) {
        Application application = Application.getInstance();

        CommandExecutor commandExecutor = InjectionUtils.provideExecutor();
        HsvCommand hsvCommand = InjectionUtils.provideHsv();

        HsvRequest hsvRequest = application.createHsvRequest(args);
        HsvResponse hsvResponse;

        try {
            hsvResponse = commandExecutor.doExecute(hsvCommand, hsvRequest)
                    .orElse(application.createHsvResponse());
        } catch (Exception e) {
            hsvResponse = application.createHsvResponse();
        }

        application.printHsvResponse(hsvResponse);
    }

    private HsvRequest createHsvRequest(String[] args) {
        String imagePath = args[FIRST_INDEX];

        return HsvRequest.builder()
                .filePath(imagePath)
                .build();
    }

    private HsvResponse createHsvResponse() {
        return HsvResponse.builder()
                .red(INITIAL_VALUE)
                .yellow(INITIAL_VALUE)
                .green(INITIAL_VALUE)
                .cyan(INITIAL_VALUE)
                .blue(INITIAL_VALUE)
                .magenta(INITIAL_VALUE)
                .build();
    }

    private void printHsvResponse(HsvResponse hsvResponse) {
        System.out.println(hsvResponse.getRed());
        System.out.println(hsvResponse.getYellow());
        System.out.println(hsvResponse.getGreen());
        System.out.println(hsvResponse.getCyan());
        System.out.println(hsvResponse.getBlue());
        System.out.println(hsvResponse.getMagenta());
    }
}
