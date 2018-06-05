package com.faza.example.simple.image.retrival.java.application.command.hsv.implementation;

import com.faza.example.simple.image.retrival.java.application.command.hsv.HsvCommand;
import com.faza.example.simple.image.retrival.java.application.command.model.request.HsvRequest;
import com.faza.example.simple.image.retrival.java.application.command.model.response.HsvResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 5 June 2018
 */

public class HsvCommandImpl implements HsvCommand {

    private static final Integer FIRST_INDEX = 0;

    private static final Integer INITIAL_VALUE = 0;

    private static final Integer HUE_INDEX = 0;

    private static final Integer SATURATION_INDEX = 1;

    private static final Integer PERCENTAGE_VALUE = 100;

    private static final Integer MINIMUM_WHITE = 10;

    private static final Integer MINIMUM_HUE = 0;

    private static final Integer MAXIMUM_HUE = 360;

    private static final Integer MAXIMUM_RED = 30;

    private static final Integer MAXIMUM_YELLOW = 90;

    private static final Integer MAXIMUM_GREEN = 150;

    private static final Integer MAXIMUM_CYAN = 210;

    private static final Integer MAXIMUM_BLUE = 270;

    private static final Integer MAXIMUM_MAGENTA = 330;

    private static final Integer INCREMENT_VALUE = 1;

    private static HsvCommandImpl sInstance;

    private HsvCommandImpl() {
        // Default constructor
    }

    public static HsvCommandImpl getInstance() {
        if (sInstance == null)
            sInstance = new HsvCommandImpl();

        return sInstance;
    }

    @Override
    public Optional<HsvResponse> execute(HsvRequest hsvRequest) throws Exception {
        BufferedImage image = getImage(hsvRequest.getFilePath());
        HsvResponse hsvResponse = createHsvResponse();

        doSearchHsvHistogram(image, hsvResponse);

        return Optional.of(hsvResponse);
    }

    private BufferedImage getImage(String filePath) throws IOException {
        return ImageIO.read(new FileInputStream(filePath));
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

    private void doSearchHsvHistogram(BufferedImage image, HsvResponse hsvResponse) {
        for (Integer x = FIRST_INDEX; x < image.getHeight(); x++) {
            for (Integer y = FIRST_INDEX; y < image.getWidth(); y++) {
                float[] hsv = getHsvColor(image, x, y);

                if (!isWhite(hsv[SATURATION_INDEX]))
                    checkHue(hsv[HUE_INDEX], hsvResponse);
            }
        }
    }

    private float[] getHsvColor(BufferedImage image, Integer x, Integer y) {
        Color color = getRGBColor(image, x, y);
        return Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
    }

    private Color getRGBColor(BufferedImage image, Integer x, Integer y) {
        Integer color = image.getRGB(x, y);
        return new Color(color);
    }

    private Boolean isWhite(Float saturation) {
        return getPercentageOfHsvValue(saturation) <= MINIMUM_WHITE;
    }

    private Float getPercentageOfHsvValue(Float value) {
        return value * PERCENTAGE_VALUE;
    }

    private void checkHue(Float hue, HsvResponse hsvResponse) {
        hue = getDegreeOfHueValue(hue);

        if (isRed(hue))
            hsvResponse.setRed(incrementValue(hsvResponse.getRed()));
        else if (isYellow(hue))
            hsvResponse.setYellow(incrementValue(hsvResponse.getYellow()));
        else if (isGreen(hue))
            hsvResponse.setGreen(incrementValue(hsvResponse.getGreen()));
        else if (isCyan(hue))
            hsvResponse.setCyan(incrementValue(hsvResponse.getCyan()));
        else if (isBlue(hue))
            hsvResponse.setBlue(incrementValue(hsvResponse.getBlue()));
        else if (isMagenta(hue))
            hsvResponse.setMagenta(incrementValue(hsvResponse.getMagenta()));
    }

    private Float getDegreeOfHueValue(Float hue) {
        return hue * MAXIMUM_HUE;
    }

    private Boolean isRed(Float hue) {
        return (hue >= MINIMUM_HUE
                && hue <= MAXIMUM_RED)
                || (hue > MAXIMUM_MAGENTA
                && hue <= MAXIMUM_HUE);
    }

    private Boolean isYellow(Float hue) {
        return hue > MAXIMUM_RED
                && hue <= MAXIMUM_YELLOW;
    }

    private Boolean isGreen(Float hue) {
        return hue > MAXIMUM_YELLOW
                && hue <= MAXIMUM_GREEN;
    }

    private Boolean isCyan(Float hue) {
        return hue > MAXIMUM_GREEN
                && hue <= MAXIMUM_CYAN;
    }

    private Boolean isBlue(Float hue) {
        return hue > MAXIMUM_CYAN
                && hue <= MAXIMUM_BLUE;
    }

    private Boolean isMagenta(Float hue) {
        return hue > MAXIMUM_BLUE
                && hue <= MAXIMUM_MAGENTA;
    }

    private Integer incrementValue(Integer value) {
        return value + INCREMENT_VALUE;
    }
}
