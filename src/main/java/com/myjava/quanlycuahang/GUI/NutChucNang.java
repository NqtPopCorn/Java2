package com.myjava.quanlycuahang.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class NutChucNang extends JToggleButton{
    public NutChucNang(String text) {
        super(text);
        this.setBackground(Color.WHITE);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ev) {
                actionPerform();
            }
        });
    }

    public void actionPerform() {
        if (this.isSelected()) {
            this.setBackground(Color.BLUE);
        } else {
            this.setBackground(Color.WHITE);
        }
    }
}
