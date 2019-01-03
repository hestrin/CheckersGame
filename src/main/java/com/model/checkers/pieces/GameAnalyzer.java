package com.model.checkers.pieces;

import com.model.board.Direction;
import com.model.checkers.GamePosition;
import com.model.checkers.Move;
import com.model.checkers.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameAnalyzer {

    public static GamePosition makeMove(GamePosition position, Move move) {
        Piece piece = position.getGameBoard()[move.getiStart()][move.getjStart()].getPiece();
        position.getGameBoard()[move.getiStart()][move.getjStart()].setPiece(null);
        position.getGameBoard()[move.getiStart()+move.getiDelta()][move.getjStart()+move.getjDelta()].setPiece(piece);
        if(move.isTaking())
            removeCapturedPieces(position, move);
        if(pawnReachedLastLine(position, move, piece))
            piece.promote();
        position.switchLastMoved();
        return position;
    }

    private static boolean pawnReachedLastLine(GamePosition position, Move move, Piece piece) {
        return ((move.getiStart()+move.getiDelta() == 0 && position.getLastMoved().getOpponent().getDirection() == -1) ||
                ( move.getiStart()+move.getiDelta() == 7 && position.getLastMoved().getOpponent().getDirection() == 1))
                && !piece.isPromoted();
    }

    private static void removeCapturedPieces(GamePosition position, Move move) {
        for(CapturedPiece capturedPiece : move.getCapturedPieces())
            position.getGameBoard()[capturedPiece.getRankIndex()][capturedPiece.getFileIndex()].setPiece(null);
    }

    public static List<Move> getListOfAllAvailableMoves(GamePosition position, Player nextPlayerToMove) {
        return Arrays.asList(Player.WHITE, Player.BLACK).contains(nextPlayerToMove) ?
                getAllowedMoves(position, nextPlayerToMove) : null;
    }

    private static List<Move> getAllowedMoves(GamePosition position, Player player) {
        List<Move> moves = new ArrayList<>();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++) {
                if(position.getGameBoard()[i][j].isOccupiedBy(player)) {
                    if(position.getGameBoard()[i][j].getPiece().isPromoted()){
                        moves.addAll(getKingMoves(position, i,j, player));
                    }
                    else
                        moves.addAll(getPawnMoves(position, player, i, j));
                }
            }
        }
        return moves;
    }

    private static List<Move> getPawnMoves(GamePosition position, Player player, int i, int j) {
        List<Move> moves = new ArrayList<>();
        for(Direction d : Arrays.asList(Direction.LEFT_UP, Direction.RIGHT_UP)) {
            int iDelta = d.getiDelta()*player.getDirection();
            int jDelta = d.getjDelta()*player.getDirection();
            if(isNewPositionInBoardBounds(i + iDelta, j + jDelta) &&
                    position.getGameBoard()[i + iDelta][j + jDelta].isUnoccupied()) {
                Move cm = new Move(i, j, iDelta, jDelta);
                moves.add(cm);
            }
        }
        List<Move> captures = getPawnCaptures(position, i, j, player, null, i, j, new ArrayList<Direction>());
        moves.addAll(captures);
        return moves;
    }

    private static List<Direction> getAllowedDirections(GamePosition position, int i, int j, Direction forbiddenDirection,
                                                 int originI, int originJ, List<Direction> forbiddenDirectionsAtOrigin) {
        List<Direction> directions = new ArrayList<>();
        for(Direction dir : Direction.directionsWithout(forbiddenDirection)) {
            if (i == originI && j == originJ) {
                if(!forbiddenDirectionsAtOrigin.contains(dir))
                    directions.add(dir);
            }
            else
                directions.add(dir);
        }
        return directions;
    }

    private static List<Move> getPawnCaptures(GamePosition position, int i, int j, Player player, Direction forbiddenDirection,
                                       int originI, int originJ, List<Direction> forbiddenDirectionsAtOrigin) {
        List<Move> capturingMoves = new ArrayList<>();
        List<Move> tmpCaptures;
        for(Direction captureDirection : getAllowedDirections(position,i,j,forbiddenDirection, originI, originJ, forbiddenDirectionsAtOrigin))
        {
            if(canPawnCapture(position,i, j, player, captureDirection, originI, originJ)) {
                if(i==originI && j == originJ)
                    forbiddenDirectionsAtOrigin.add(captureDirection);
                tmpCaptures = getPawnCaptures(position,i+2 * player.getDirection() * captureDirection.getiDelta(),
                        j+2 * player.getDirection() * captureDirection.getjDelta(), player,
                        captureDirection.getOppositeDirection(), originI, originJ, forbiddenDirectionsAtOrigin);
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

    private static List<Move> getKingMoves(GamePosition position, int i, int j, Player player) {
        List<Move> moves = new ArrayList<>();
        moves.addAll(getNormalKingsMoves(position,i, j));
        moves.addAll(getCaptureKingsMoves(position,i, j, player));
        return moves;
    }

    private static List<Move> getCaptureKingsMoves(GamePosition position, int i, int j, Player player) {
        List<Move> captures = new ArrayList<>();
        for(Direction d : Direction.values()) {
            for(int k=1; k<7; k++) {
                int iDelta = k*d.getiDelta()*player.getDirection();
                int jDelta = k*d.getjDelta()*player.getDirection();

                if(!isNewPositionInBoardBounds(i+iDelta, j+jDelta))
                    break;

                if(position.getGameBoard()[i+iDelta][j+jDelta].isOccupiedBy(player))
                    break;

                if(position.getGameBoard()[i+iDelta][j+jDelta].isOccupiedBy(player.getOpponent()) &&
                        allCellsBetweenAreUnoccupied(position,i,j,k, d, player)) {
                    int m = k+1;
                    while(m < 7) {
                        int iMDelta = m*d.getiDelta()*player.getDirection();
                        int jMDelta = m*d.getjDelta()*player.getDirection();

                        if(isNewPositionInBoardBounds(i+iMDelta, j+jMDelta) &&
                                position.getGameBoard()[i+iMDelta][j+jMDelta].isUnoccupied() &&
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

    private static boolean allCellsBetweenAreUnoccupied(GamePosition position, int iStart, int jStart, int steps, Direction d, Player player) {
        boolean allUnoccupied = true;
        for (int k=1; k<steps; k++) {
            int iDelta = k*d.getiDelta()*player.getDirection();
            int jDelta = k*d.getjDelta()*player.getDirection();
            if(!position.getGameBoard()[iStart+iDelta][jStart+jDelta].isUnoccupied())
                allUnoccupied = false;
        }
        return allUnoccupied;
    }

    private static List<Move> getNormalKingsMoves(GamePosition position, int i, int j) {
        List<Move> normalMoves = new ArrayList<>();
        for(Direction d : Direction.values()) {
            for (int k = 1; k <= 7; k++) {
                if (isNewPositionInBoardBounds(i + d.getiDelta()*k, j + d.getjDelta()*k) &&
                        position.getGameBoard()[i + d.getiDelta()*k][j + d.getjDelta()*k].isUnoccupied())
                    normalMoves.add(new Move(i, j, d.getiDelta()*k, d.getjDelta()*k, false, true ));
                else
                    break;
            }
        }
        return normalMoves;
    }

    private static boolean canPawnCapture(GamePosition position, int i, int j, Player player, Direction direction, int originI, int originJ) {
        int iEnd = i + 2*player.getDirection()* direction.getiDelta();
        int jEnd = j + 2*player.getDirection()* direction.getjDelta();
        int iToCapture = i + 1*player.getDirection()* direction.getiDelta();
        int jToCapture = j + 1*player.getDirection()* direction.getjDelta();

        return isNewPositionInBoardBounds(iEnd, jEnd)
                && position.getGameBoard()[iToCapture][jToCapture].isOccupiedBy(player.getOpponent())
                && (position.getGameBoard()[iEnd][jEnd].isUnoccupied() || (originI == iEnd && originJ == jEnd));
    }

    private static boolean isNewPositionInBoardBounds(int i, int j) {
        return 0 <= i && i <= 7 && 0 <= j && j <= 7;
    }

}
