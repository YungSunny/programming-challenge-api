package com.programming.competition.game.service;

import com.programming.competition.game.enums.ResponseMessages;
import com.programming.competition.game.model.Player;
import com.programming.competition.game.repository.PlayerRepository;
import com.programming.competition.game.service.impl.PlayerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    public void shouldThrowErrorIfNoName() {

        //given
        String name = null;
        String taskName = "First";

        //when
        try {
            playerService.addPlayerScore(name, taskName);
        }

        //then
        catch (ResponseStatusException e) {
            assertEquals(e.getReason(), ResponseMessages.PLAYER_NOT_FOUND.getMessage());
            assertEquals(e.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    public void shouldAddNewPlayer() {

        //given
        String name = "Max";
        String taskName = "First";

        //when
        playerService.addPlayerScore(name, taskName);

        //then
        verify(playerRepository).addPlayer(ArgumentMatchers.any(Player.class));
    }

    @Test
    public void shouldNotLetSubmitSameTaskTwice() {

        //given
        String name = "Max";
        String task = "First";
        List<String> completedTasks = new ArrayList<>();
        completedTasks.add(task);
        Player player = new Player(name, 1, completedTasks);
        List<Player> playerList = new ArrayList<>();
        playerList.add(player);
        when(playerRepository.getPlayerList()).thenReturn(playerList);

        //when
        try {
            playerService.addPlayerScore(name, task);
        }
        //then
        catch (ResponseStatusException e) {
            assertEquals(e.getReason(), ResponseMessages.ALREADY_COMPLETED.getMessage());
            assertEquals(e.getStatus(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Test
    public void shouldGetAllPlayers() {

        //when
        playerService.getAllPlayers();

        //then
        verify(playerRepository).getPlayerList();
    }
}
