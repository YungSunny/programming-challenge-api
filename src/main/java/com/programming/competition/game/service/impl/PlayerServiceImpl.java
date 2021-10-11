package com.programming.competition.game.service.impl;

import com.programming.competition.game.enums.ResponseMessages;
import com.programming.competition.game.model.Player;
import com.programming.competition.game.repository.PlayerRepository;
import com.programming.competition.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@Service
public class PlayerServiceImpl implements PlayerService<Player> {

    @Autowired
    private PlayerRepository playerRepository;

    public void addPlayerScore(String playerName, String taskName) {
        Player currentPlayer = playerRepository.getPlayerList().stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseGet(() -> createPlayer(playerName));
        checkPlayer(currentPlayer, taskName);
    }

    private Player createPlayer(String playerName) {
        Player player = new Player(playerName);
        playerRepository.getPlayerList().add(player);
        return player;
    }

    private void checkPlayer(Player player, String taskName) {
        boolean notCompletedTask = player.getCompletedTasksNames().stream()
                .noneMatch(task -> task.equals(taskName));
        if (notCompletedTask) {
            player.setScore(player.getScore() + 1);
            player.getCompletedTasksNames().add(taskName);
        } else {
            throw new ResponseStatusException(NOT_ACCEPTABLE, ResponseMessages.ALREADY_COMPLETED.getMessage());
        }
    }

    public List<Player> getAllPlayers() {
        return playerRepository.getPlayerList();
    }
}
