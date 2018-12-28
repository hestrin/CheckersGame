package com.mainApp.services.checkers;

import com.model.board.BoardType;
import org.springframework.stereotype.Component;

@Component
public interface BoardService {
    String drawBoard(BoardType type);
}
