package com.programming.competition.game.service;

import com.programming.competition.game.model.Player;

import java.util.List;

/**
 * Service for player related operations
 */
public interface PlayerService<U> {

    /**
     * Registers player score to the system. This method should be
     * called only when correct solution is present
     *
     * @param playerName name of the player which score should be added
     * @param taskName   name of the task that correct solution is provided
     */
    void addPlayerScore(String playerName, String taskName);

    /**
     * Gets all available players who solved at least one task
     *
     * @return all the players
     */
    List<U> getAllPlayers();
}
