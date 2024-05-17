package com.myjava.quanlycuahang.GUI.test;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;

public class DemoIconButton {
    public static void main(String[] args) {
        // Tạo JFrame
        JFrame frame = new JFrame("Demo Icon Button");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Tạo ImageIcon
        ImageIcon icon = new ImageIcon("C:/Users/truon/Downloads/icon/icons8-add-50.png");
        //lam icon nho lai
        icon = new ImageIcon(icon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));

        // Tạo JButton với icon
        JButton btnWithIcon = new JButton();
        // button co background trong suot va xoa border
        btnWithIcon.setContentAreaFilled(false);
        btnWithIcon.setBorder(null);
        btnWithIcon.setIcon(icon);

        btnWithIcon.addActionListener(e -> {
            System.out.println("Button with icon clicked");
        });

        // Thêm JButton vào JFrame
        frame.add(btnWithIcon);

        // Hiển thị JFrame
        frame.setVisible(true);
    }
}