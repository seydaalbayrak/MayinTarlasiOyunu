package com.company;

import javax.swing.*;

public class Buttons extends JButton {
    private int row,col,count;
    private boolean death,kill;

    public Buttons(int row, int col) {
        this.row = row;
        this.col = col;
        this.count = 0;
        this.death = false;
        this.kill = false;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isDeath() {
        return death;
    }

    public void setMine(boolean death) {
        this.death = death;
    }

    public boolean isKill() {
        return kill;
    }

    public void setKill(boolean kill) {
        this.kill = kill;
    }

}
