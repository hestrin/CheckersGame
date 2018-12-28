package com.mainApp.services.checkers.impl;

import com.mainApp.services.checkers.BoardService;
import com.model.board.BoardType;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@ComponentScan
@Component
public class BoardServiceImpl implements BoardService {

    private static final String UPPER_PREFIX= "\n\n\n\n\n\n\n\n";
    private static final String LINE_PREFIX = "\t\t\t\t\t\t\t\t";

    public String drawBoard(BoardType type) {
        switch (type) {
            case CHESS:
                return drawChessBoard();
            case CHECKERS:
            default:
                return drawCheckersBoard();
        }
    }

    public String drawCheckersBoard() {
        return drawChessBoard();
    }

    public String drawChessBoard() {
        StringBuilder sb = new StringBuilder(UPPER_PREFIX);
        sb.append(horizontalLetterLine());
        for (int i=8; i>0; i--) {
            sb.append(horizontalBoardLine());
            sb.append(horizontalRankLine(i));
        }
        sb.append(horizontalBoardLine());
        sb.append(horizontalLetterLine());
        return sb.toString();
    }

    public String horizontalRankLine(int rankNumber) {
        StringBuilder sb = new StringBuilder(Integer.toString(rankNumber) + " |");
        sb.append(String.join("", Collections.nCopies(8, "   |")));
        sb.append(" " + Integer.toString(rankNumber));
        return LINE_PREFIX + sb.append("\n").toString();
    }

    public String horizontalBoardLine() {
        String str1 = String.join("", Collections.nCopies(8, "+---"));
        return LINE_PREFIX + "--" + str1 + "+--\n";
    }

    public String horizontalLetterLine() {
        List letters = Arrays.asList("ABCDEFGH".toCharArray());
        String str1 = "";
        for(Character letter : "ABCDEFGH".toCharArray())
            str1 += "| " + letter + " ";
        return LINE_PREFIX + "  " + str1 + "|\n";
    }
}
