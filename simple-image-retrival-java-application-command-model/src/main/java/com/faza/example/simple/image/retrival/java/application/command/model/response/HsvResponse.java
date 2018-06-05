package com.faza.example.simple.image.retrival.java.application.command.model.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 5 June 2018
 */

@Data
@Builder
public class HsvResponse {

    private Integer red;

    private Integer yellow;

    private Integer green;

    private Integer cyan;

    private Integer blue;

    private Integer magenta;
}
