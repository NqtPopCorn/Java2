package com.myjava.quanlycuahang.GUI.test;

import java.awt.Rectangle;

import javax.security.auth.login.AccountLockedException;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class test {
    static public class SimpleJFrameExample {
        public static void main(String[] args) {
            // Create a new JFrame
            JFrame frame = new JFrame("Simple JFrame Example");

            // Set the size of the JFrame
            frame.setSize(500, 400);

            // Set the default operation when the JFrame is closed
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Add a JLabel to the JFrame
            frame.getContentPane().add(new FormDangNhapPanel());

            // Make the JFrame visible
            frame.setVisible(true);
        }
}
    public static void main(String[] args) {
        SimpleJFrameExample.main(args);
        
    }
}
