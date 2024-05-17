package com.myjava.quanlycuahang.GUI.test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestMenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JFrame Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(200, frame.getHeight()));

        JPanel mainPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        NutChucNang button1 = new NutChucNang("Button 1");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Card 1");
            }
        });

        NutChucNang button2 = new NutChucNang("Button 2");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Card 2");
            }
        });

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(button1);
        buttonGroup.add(button2);

        sidePanel.add(button1);
        sidePanel.add(button2);

        sidePanel.add(button1);
        sidePanel.add(button2);

        JPanel card1 = new JPanel();
        card1.add(new JLabel("Card 1"));
        mainPanel.add(card1, "Card 1");

        JPanel card2 = new JPanel();
        card2.add(new JLabel("Card 2"));
        mainPanel.add(card2, "Card 2");

        frame.getContentPane().add(sidePanel, BorderLayout.WEST);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        // frame.pack();
        frame.setVisible(true);
    }
}