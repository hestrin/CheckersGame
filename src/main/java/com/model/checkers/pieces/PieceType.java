package com.model.checkers.pieces;

import java.util.List;

public enum PieceType {
    PAWN("P"), KING("K");

    private String code;
    private List<Movement> allowedDeltas;

    PieceType(String aCode) {
        this.code = aCode;
    }

}
