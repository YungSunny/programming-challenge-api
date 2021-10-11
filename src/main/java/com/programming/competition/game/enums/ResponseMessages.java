package com.programming.competition.game.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessages {

    CORRECT("Task solved correctly. Result is saved"),
    INCORRECT("Task solved incorrectly. Try again"),
    TASK_ERROR("Can not provide tasks, please try again later"),
    SEND_ERROR("Error while sending request to external services"),
    ALREADY_COMPLETED("Player already completed this task"),
    PARSING_ERROR("Problem occurred while parsing api response"),
    SOLUTION_NOT_FOUND("Please provide task solution code"),
    PLAYER_NOT_FOUND("Please add your name");

    private final String message;
}
