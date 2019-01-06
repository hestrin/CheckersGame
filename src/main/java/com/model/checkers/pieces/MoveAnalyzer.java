package com.model.checkers.pieces;

import com.mainApp.services.checkers.BoardService;
import com.model.board.Direction;
import com.model.checkers.Coordinates;
import com.model.checkers.Game;
import com.model.checkers.Move;
import com.model.checkers.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MoveAnalyzer {

    @Autowired
    private BoardService boardService;

    public static Game makeMove(Game position, Move move) {
        Piece piece = position.getBoard()[move.getiStart()][move.getjStart()].getPiece();
        position.getBoard()[move.getiStart()][move.getjStart()].setPiece(null);
        position.getBoard()[move.getiStart()+move.getiDelta()][move.getjStart()+move.getjDelta()].setPiece(piece);
        if(move.isTaking())
            removeCapturedPieces(position, move);
        if(isPawnOnLastRank(position, move, piece))
            piece.promote();
        position.addToHistory(move);
        position.completeMove();
        return position;
    }

    private static boolean isPawnOnLastRank(Game position, Move move, Piece piece) {
        return ((move.getiStart()+move.getiDelta() == 0 && position.getLastMoved().getOpponent().getDirection() == -1) ||
                ( move.getiStart()+move.getiDelta() == 7 && position.getLastMoved().getOpponent().getDirection() == 1))
                && !piece.isPromoted();
    }

    private static void removeCapturedPieces(Game position, Move move) {
        for(CapturedPiece capturedPiece : move.getCapturedPieces())
            position.getBoard()[capturedPiece.getRankIndex()][capturedPiece.getFileIndex()].setPiece(null);
    }

    public static List<Move> getAllowedMoves(Game position) {
        Player player = position.getPlayerOnTheMove();
        List<Move> moves = new ArrayList<>();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                if(position.getBoard()[i][j].isOccupiedBy(player)) {
                    if(position.getBoard()[i][j].getPiece().isPromoted()){
                        moves.addAll(getKingMoves(position, i,j, player));
                    }
                    else
                        moves.addAll(getPawnMoves(position, player, i, j));
                }
            }
        }
        return moves;
    }

    public static List<Move> getPawnMoves(Game position, Player player, int i, int j) {
        List<Move> moves = new ArrayList<>();
        for(Direction d : Arrays.asList(Direction.LEFT_UP, Direction.RIGHT_UP)) {
            int iDelta = d.getiDelta()*player.getDirection();
            int jDelta = d.getjDelta()*player.getDirection();
            if(isNewPositionInBoardBounds(i + iDelta, j + jDelta) &&
                    position.getBoard()[i + iDelta][j + jDelta].isUnoccupied()) {
                Move cm = new Move(i, j, iDelta, jDelta);
                moves.add(cm);
            }
        }
        List<Move> captures = getPawnCaptures(position, i, j, player, null, i, j, null);
        moves.addAll(captures);
        return moves;
    }

    private static List<Direction> getAllowedDirections(int i, int j,
                                                        Direction forbiddenDirection,
                                                        Map<Coordinates, List<Direction>> forbiddenDirections) {
        List<Direction> directions = new ArrayList<>();
        for(Direction dir : Direction.directionsWithout(forbiddenDirection)) {
            if(forbiddenDirections != null) {
                List<Direction> forbiddenDirectionsAtIJ = forbiddenDirections.get(new Coordinates(i, j));
                if (forbiddenDirectionsAtIJ == null || !forbiddenDirectionsAtIJ.contains(dir))
                    directions.add(dir);
            }
            else
                directions.add(dir);
        }
        return directions;
    }

    public static List<Move> getPawnCaptures(Game position, int i, int j, Player player, Direction forbiddenDirection,
                                              int originI, int originJ, Map<Coordinates, List<Direction>> forbiddenDirections) {
        List<Move> capturingMoves = new ArrayList<>();
        List<Move> tmpCaptures;
        for(Direction captureDirection : getAllowedDirections(i, j, forbiddenDirection, forbiddenDirections))
        {
            if(canPawnCapture(position,i, j, player, captureDirection, originI, originJ)) {
//                System.out.println( "Pawn capture i:" + i + " j:" + j + " direction :" + captureDirection);
                if(forbiddenDirections == null)
                    forbiddenDirections = new HashMap<>();
                List<Direction> forbiddenDirectionsAtIJ = forbiddenDirections.get(new Coordinates(i,j));
                if(forbiddenDirectionsAtIJ != null) {
                    forbiddenDirectionsAtIJ.add(captureDirection);
                }
                else
                {
                    forbiddenDirectionsAtIJ = new ArrayList<>();
                    forbiddenDirectionsAtIJ.add(captureDirection);
                    forbiddenDirections.put(new Coordinates(i,j), forbiddenDirectionsAtIJ);
                }
                tmpCaptures = getPawnCaptures(position,i+2 * player.getDirection() * captureDirection.getiDelta(),
                        j+2 * player.getDirection() * captureDirection.getjDelta(), player,
                        captureDirection.getOppositeDirection(), originI, originJ, forbiddenDirections);
                CapturedPiece prevCapturedPiece = new CapturedPiece(
                        i + player.getDirection() * captureDirection.getiDelta(),
                        j + player.getDirection() * captureDirection.getjDelta());
                if(tmpCaptures.size() > 0) {
                    for(Move capture : tmpCaptures) {
                        if(!capture.getCapturedPieces().contains(prevCapturedPiece)) {
                            Move cm = new Move(i, j,
                                    (2 * player.getDirection() * captureDirection.getiDelta()) + capture.getiDelta(),
                                    (2 * player.getDirection() * captureDirection.getjDelta()) + capture.getjDelta(),
                                    true, false);
                            cm.getCapturedPieces().addAll(capture.getCapturedPieces());
                            cm.getCapturedPieces().add(new CapturedPiece(
                                    i + player.getDirection() * captureDirection.getiDelta(),
                                    j + player.getDirection() * captureDirection.getjDelta()));
                            capturingMoves.add(cm);
                        }
                    }
                }
                else {
                    Move cm = new Move(i, j,
                            2 * player.getDirection() * captureDirection.getiDelta(),
                            2 * player.getDirection() * captureDirection.getjDelta(),
                            true, false);
                    cm.getCapturedPieces().add(prevCapturedPiece);
                    capturingMoves.add(cm);
                }
            }
        }
        return capturingMoves;
    }

    public static List<Move> getKingMoves(Game position, int i, int j, Player player) {
        List<Move> moves = new ArrayList<>();
        moves.addAll(getNormalKingsMoves(position,i, j));
        moves.addAll(getCaptureKingsMoves(position,i, j, player));
        return moves;
    }

    private static List<Move> getCaptureKingsMoves(Game position, int i, int j, Player player) {
        // for now only single taking for king type pawns is allowed.
        //  TODO allow multiple takings
        List<Move> captures = new ArrayList<>();
        for(Direction d : Direction.values()) {
            for(int k=1; k<7; k++) {
                int iDelta = k*d.getiDelta()*player.getDirection();
                int jDelta = k*d.getjDelta()*player.getDirection();

                if(!isNewPositionInBoardBounds(i+iDelta, j+jDelta))
                    break;

                if(position.getBoard()[i+iDelta][j+jDelta].isOccupiedBy(player))
                    break;

                if(position.getBoard()[i+iDelta][j+jDelta].isOccupiedBy(player.getOpponent()) &&
                        allCellsBetweenAreUnoccupied(position,i,j,k, d, player)) {
                    int m = k+1;
                    while(m < 7) {
                        int iMDelta = m*d.getiDelta()*player.getDirection();
                        int jMDelta = m*d.getjDelta()*player.getDirection();

                        if(isNewPositionInBoardBounds(i+iMDelta, j+jMDelta) &&
                                position.getBoard()[i+iMDelta][j+jMDelta].isUnoccupied() &&
                                allCellsBetweenAreUnoccupied(position,i+iDelta, j+jDelta, m-k, d, player)) {
                            Move cm = new Move(i, j,
                                    m * player.getDirection() * d.getiDelta(),
                                    m * player.getDirection() * d.getjDelta(),
                                    true, false);
                            cm.getCapturedPieces().add(new CapturedPiece(i+iDelta, j+jDelta));
                            captures.add(cm);
                        }
                        m++;
                    }
                    k=7;
                }
            }
        }
        return captures;
    }

    private static boolean allCellsBetweenAreUnoccupied(Game position, int iStart, int jStart, int steps, Direction d, Player player) {
        boolean allUnoccupied = true;
        for (int k=1; k<steps; k++) {
            int iDelta = k*d.getiDelta()*player.getDirection();
            int jDelta = k*d.getjDelta()*player.getDirection();
            if(!position.getBoard()[iStart+iDelta][jStart+jDelta].isUnoccupied())
                allUnoccupied = false;
        }
        return allUnoccupied;
    }

    private static List<Move> getNormalKingsMoves(Game position, int i, int j) {
        List<Move> normalMoves = new ArrayList<>();
        for(Direction d : Direction.values()) {
            for (int k = 1; k <= 7; k++) {
                if (isNewPositionInBoardBounds(i + d.getiDelta()*k, j + d.getjDelta()*k) &&
                        position.getBoard()[i + d.getiDelta()*k][j + d.getjDelta()*k].isUnoccupied())
                    normalMoves.add(new Move(i, j, d.getiDelta()*k, d.getjDelta()*k, false, true ));
                else
                    break;
            }
        }
        return normalMoves;
    }

    private static boolean canPawnCapture(Game position, int i, int j, Player player, Direction direction, int originI, int originJ) {
        int iEnd = i + 2*player.getDirection()* direction.getiDelta();
        int jEnd = j + 2*player.getDirection()* direction.getjDelta();
        int iToCapture = i + 1*player.getDirection()* direction.getiDelta();
        int jToCapture = j + 1*player.getDirection()* direction.getjDelta();

        return isNewPositionInBoardBounds(iEnd, jEnd)
                && position.getBoard()[iToCapture][jToCapture].isOccupiedBy(player.getOpponent())
                && (position.getBoard()[iEnd][jEnd].isUnoccupied() || (originI == iEnd && originJ == jEnd));
    }

    private static boolean isNewPositionInBoardBounds(int i, int j) {
        return 0 <= i && i <= 7 && 0 <= j && j <= 7;
    }

    public BoardService getBoardService() {
        return boardService;
    }

    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }
}
