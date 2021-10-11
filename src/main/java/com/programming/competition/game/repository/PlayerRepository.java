package com.programming.competition.game.repository;

import com.programming.competition.game.model.Player;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Repository
public class PlayerRepository {

    private List<Player> playerList = new ArrayList<>();
}
