package com.programming.competition.game.controller;

import com.programming.competition.game.model.Player;
import com.programming.competition.game.service.PlayerService;
import com.programming.competition.game.service.impl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService<Player> playerService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Player>> getAllPlayer() {
        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
    }
}
