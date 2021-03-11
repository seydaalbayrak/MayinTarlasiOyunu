package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MineSweeper implements MouseListener {
    JFrame frame;
    Buttons[][] board = new Buttons[10][10];
    int openButton;

    public MineSweeper() {
        openButton = 0;
        frame = new JFrame("Mine Sweeper");
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                Buttons button = new Buttons(row, col);
                frame.add(button);
                button.addMouseListener(this);
                board[row][col] = button;
            }
        }
        generateDeath();
        updateCount();
        frame.setVisible(true);
    }

    public void generateDeath() {
        int i = 0;
        while (i < 10) {
            int randRow = (int) (Math.random() * board.length);
            int randCol = (int) (Math.random() * board[0].length);

            while (board[randRow][randCol].isDeath()) {
                randRow = (int) (Math.random() * board.length);
                randCol = (int) (Math.random() * board[0].length);
            }
            board[randRow][randCol].setMine(true);
            i++;
        }
    }

    public void print() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isDeath()) {
                    board[row][col].setIcon(new ImageIcon("death.png"));
                } else {
                    board[row][col].setText(board[row][col].getCount() + "");
                    board[row][col].setEnabled(false);
                }
            }
        }
    }


    public void updateCount() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isDeath()) {
                    counting(row, col);
                }
            }
        }
    }

    public void counting(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int k = col - 1; k <= col + 1; k++) {
                try {
                    int value = board[i][k].getCount();
                    board[i][k].setCount(++value);
                } catch (Exception e) {

                }
            }
        }
    }

    public void open(int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c].getText().length() > 0
                || board[r][c].isEnabled() == false) {
            return;
        } else if (board[r][c].getCount() != 0) {
            board[r][c].setText(board[r][c].getCount() + "");
            board[r][c].setEnabled(false);
            openButton++;
        } else {
            openButton++;
            board[r][c].setEnabled(false);
            open(r - 1, c);
            open(r + 1, c);
            open(r, c - 1);
            open(r, c + 1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Buttons button = (Buttons) e.getComponent();
        if (e.getButton() == 1) {
            if (button.isDeath()) {
                JOptionPane.showMessageDialog(frame, "Game Over !");
                print();
            } else {
                open(button.getRow(), button.getCol());
                if (openButton == (board.length * board[0].length) - 10) {
                    JOptionPane.showMessageDialog(frame, "Congratulations. You won !");
                    print();
                }
            }
        } else if (e.getButton() == 3) {
            if (!button.isKill()) {
                button.setIcon(new ImageIcon("kill.png"));
                button.setKill(true);
            } else {
                button.setIcon(null);
                button.setKill(false);
            }

        }

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }


}
