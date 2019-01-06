package com.mainApp.services.checkers;

import com.model.checkers.Game;
import org.springframework.stereotype.Component;

@Component
public interface CheckersService {

    Game nextMove();
    Game showGame();
    Game restartGame();
}
