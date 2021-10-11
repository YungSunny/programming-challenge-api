package com.programming.competition.game.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Player {

    private String name;

    private long score;

    private List<String> completedTasksNames = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }
}
