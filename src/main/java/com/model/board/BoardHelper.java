package com.model.board;

public class BoardHelper {

    public static int findCellIndex(int i, int j) {
        return 110 + 92*(7-i) + j*4;
    }
}
