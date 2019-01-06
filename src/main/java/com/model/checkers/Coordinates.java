package com.model.checkers;

public class Coordinates {

    private int i;
    private int j;

    public Coordinates(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Coordinates) {
            Coordinates c = (Coordinates) obj;
            return c.i == this.i && c.j == this.j;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.i*3 + this.j*7;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
