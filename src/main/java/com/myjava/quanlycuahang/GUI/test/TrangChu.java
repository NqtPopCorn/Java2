package com.myjava.quanlycuahang.GUI.test;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TrangChu extends JFrame {
    private JPanel mainPanel;
    private JPanel btnGroupPanel;

    public TrangChu() {
        mainPanel = new FormDangNhapPanel();
        btnGroupPanel = new JPanel();

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        this.getContentPane().add(btnGroupPanel, BorderLayout.WEST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
    }
}
