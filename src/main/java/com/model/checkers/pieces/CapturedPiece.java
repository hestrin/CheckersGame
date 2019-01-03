package com.model.checkers.pieces;

public class CapturedPiece {
    private int rankIndex;
    private int fileIndex;

    public CapturedPiece(int aRankIndex, int aFileIndex) {
        this.fileIndex = aFileIndex;
        this.rankIndex = aRankIndex;
    }

    @Override
    public int hashCode() {
        return 3*rankIndex + 7*fileIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CapturedPiece))
            return false;
        return rankIndex == ((CapturedPiece) obj).getRankIndex()
                && fileIndex == ((CapturedPiece) obj).getFileIndex();
    }

    public int getRankIndex() {
        return rankIndex;
    }

    public void setRankIndex(int rankIndex) {
        this.rankIndex = rankIndex;
    }

    public int getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(int fileIndex) {
        this.fileIndex = fileIndex;
    }
}
