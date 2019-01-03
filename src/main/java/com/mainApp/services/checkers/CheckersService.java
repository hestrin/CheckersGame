package com.mainApp.services.checkers;

import com.model.checkers.GamePosition;
import org.springframework.stereotype.Component;

@Component
public interface CheckersService {

    GamePosition nextMove();
    GamePosition showGame();
    GamePosition restartGame();
}
