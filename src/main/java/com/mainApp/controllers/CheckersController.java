package com.mainApp.controllers;

import com.mainApp.services.checkers.BoardService;
import com.mainApp.services.checkers.CheckersService;
import com.model.board.BoardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckersController {

    @Autowired
    BoardService boardService;

    @Autowired
    CheckersService checkersService;

    @RequestMapping(value = "/restart",
            method=RequestMethod.GET,
            produces=MediaType.TEXT_PLAIN_VALUE)
    public String restart() { return boardService.draw(checkersService.restartGame()); }

    @RequestMapping(value = "/play",
            method=RequestMethod.GET,
            produces=MediaType.TEXT_PLAIN_VALUE)
    public String play() {
        return boardService.draw(checkersService.nextMove());
    }

    @RequestMapping(value = "/showGame",
            method=RequestMethod.GET,
            produces=MediaType.TEXT_PLAIN_VALUE)
    public String showGame() { return boardService.draw(checkersService.showGame()); }

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setCheckersService(CheckersService checkersService) {
        this.checkersService = checkersService;
    }
}
