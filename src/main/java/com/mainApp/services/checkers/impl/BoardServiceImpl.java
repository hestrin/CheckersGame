package com.mainApp.services.checkers.impl;

import com.mainApp.services.checkers.BoardService;
import com.model.board.BoardHelper;
import com.model.checkers.Game;
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

    public String draw(Game game) {
        String board = drawBoard(game);
        board = placePiecesOnBoard(board, game);
        return board;
    }

    public String drawBoard(Game game) {
        StringBuilder sb = new StringBuilder(UPPER_PREFIX);
        sb.append(horizontalLetterLine());
        for (int i=8; i>0; i--) {
            sb.append(horizontalBoardLine());
            sb.append(horizontalRankLine(i));
        }
        sb.append(horizontalBoardLine());
        sb.append(horizontalLetterLine());
        if(game.isGameFinished())
            sb.append("\n" + game.getWinner().toString() + " WINS\n");
        return sb.toString();
    }

    private String placePiecesOnBoard(String board, Game game) {
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                if(game.getBoard()[i][j].isOccupied())
                    board = placePieceOnBoard(board, i, j, game);
            }
        }
        return board;
    }

    private String placePieceOnBoard(String board, int i, int j, Game game) {
        int index = BoardHelper.findCellIndex(i,j);
        return board.substring(0, index) +
                (game.getBoard()[i][j].getPiece().getSign()) + board.substring(index+1, board.length());
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
        String lettersLine = "";
        for(Character letter : "ABCDEFGH".toCharArray())
            lettersLine += "| " + letter + " ";
        return LINE_PREFIX + "  " + lettersLine + "|\n";
    }
}
