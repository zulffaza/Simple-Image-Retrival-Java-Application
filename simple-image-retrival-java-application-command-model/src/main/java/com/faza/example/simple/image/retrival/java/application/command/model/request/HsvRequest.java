package com.faza.example.simple.image.retrival.java.application.command.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 5 June 2018
 */

@Data
@Builder
public class HsvRequest {

    private String filePath;
}
