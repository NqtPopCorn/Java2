package com.myjava.quanlycuahang.GUI.test;


import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MyIconButton extends JButton{
    private ImageIcon icon;

    public MyIconButton(String path, int icon_width, int icon_height) {
        super();
        icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(icon_width, icon_height, Image.SCALE_SMOOTH));
        this.setIcon(icon);
        this.setBorder(null);
    }
}
