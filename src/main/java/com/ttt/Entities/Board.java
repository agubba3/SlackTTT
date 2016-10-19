package com.ttt.Entities;

/**
 * Created by agubba on 10/16/16.
 */
import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {

    private ImageIcon cross = new ImageIcon("image/x.gif");
    private ImageIcon not = new ImageIcon("image/o.gif");

    public Board() {
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            int mode = (int) (Math.random() * 3.0);
            if (mode == 0)
                add(new JLabel(this.cross));
            else if (mode == 1)
                add(new JLabel(this.not));
            else
                add(new JLabel());
        }
    }

//    public static void main(String[] args) {
//        Board frame = new Board();
//        frame.setTitle("TicTacToe");
//        frame.setSize(200, 200);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(3);
//        frame.setVisible(true);
//    }
}