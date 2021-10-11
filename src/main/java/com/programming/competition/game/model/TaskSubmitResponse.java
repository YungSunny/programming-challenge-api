package com.programming.competition.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskSubmitResponse {

    private String statusCode;

    private String message;
}
