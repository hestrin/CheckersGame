package com.model.checkers.pieces;

import java.util.ArrayList;
import java.util.List;

public enum PieceType {
    PAWN("P"), KING("K");

    private String code;
    private List<CapturedPiece> allowedMoves;

    PieceType(String aCode) {
        this.code = aCode;
        switch(aCode) {
            case "K":
                this.allowedMoves = createKingMoves();
                break;
            case "P":
            default:
                this.allowedMoves = createPawnMoves();
        }
    }

    private List<CapturedPiece> createKingMoves() {
        List<CapturedPiece> kingMoves = new ArrayList<>();
        for(int i=1; i<=7; i++){
            kingMoves.add(new CapturedPiece(i,i));
            kingMoves.add(new CapturedPiece(i,-1*i));
            kingMoves.add(new CapturedPiece(-1*i,i));
            kingMoves.add(new CapturedPiece(-1*i,-1*i));
        }
        return kingMoves;
    }

    private List<CapturedPiece> createPawnMoves() {
        List<CapturedPiece> pawnMoves = new ArrayList<>();
        pawnMoves.add(new CapturedPiece(1,1));
        pawnMoves.add(new CapturedPiece(1,-1));
        return pawnMoves;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CapturedPiece> getAllowedMoves() {
        return allowedMoves;
    }

    public void setAllowedMoves(List<CapturedPiece> allowedMoves) {
        this.allowedMoves = allowedMoves;
    }
}
