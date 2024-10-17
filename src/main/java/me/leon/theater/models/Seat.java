package me.leon.theater.models;

import java.io.Serializable;

public class Seat implements Serializable {
    private int row;
    private int column;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Row: " + row + " / Column: " + column;
    }
}
