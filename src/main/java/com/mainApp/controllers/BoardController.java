package com.mainApp.controllers;

import com.mainApp.services.abcConjecture.impl.AbcConjectureServiceImpl;
import com.mainApp.services.checkers.BoardService;
import com.mainApp.services.checkers.CheckersService;
import com.mainApp.services.checkers.impl.BoardServiceImpl;
import com.model.board.BoardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    CheckersService checkersService;

    @RequestMapping(value = "/new",
            method=RequestMethod.GET,
            produces=MediaType.TEXT_PLAIN_VALUE)
    public String drawBoard() {
        return boardService.drawBoard(BoardType.CHECKERS);
    }

    @RequestMapping(value = "/checkers",
            method=RequestMethod.GET,
            produces=MediaType.TEXT_PLAIN_VALUE)
    public String checkersGame() {
        return checkersService.applyPostion(boardService.drawBoard(BoardType.CHECKERS));
    }

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setCheckersService(CheckersService checkersService) {
        this.checkersService = checkersService;
    }
}
