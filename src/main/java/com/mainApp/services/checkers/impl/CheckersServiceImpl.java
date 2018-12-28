package com.mainApp.services.checkers.impl;

import com.mainApp.services.checkers.CheckersService;
import com.model.checkers.GamePostion;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan
public class CheckersServiceImpl implements CheckersService {

    private GamePostion currentPostion;

    public String applyPostion(String board) {
        initializePositionIfNeeded();
        makeNextMove(currentPostion);
        return board;
    }

    private void initializePositionIfNeeded() {
        if(currentPostion == null)
            initializePosition();
    }

    private void initializePosition() {
        currentPostion = new GamePostion();
    }

    private void makeNextMove(GamePostion currentPostion) {
    }
}
