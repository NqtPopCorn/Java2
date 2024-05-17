package com.myjava.quanlycuahang.GUI.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import com.myjava.quanlycuahang.DAO.TaiKhoanDAO;
import com.myjava.quanlycuahang.DTO.TaiKhoanDTO;

public class LoginFrame extends JFrame {
    JTextField username;
    JPasswordField pass;

    public LoginFrame() {
        super("Dang nhap");
        setLocationRelativeTo(null);
        setSize(600,400);
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,1));

        JLabel banner = new JLabel("Dang nhap");
        banner.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel l1 = new JLabel("Ten dang nhap: ");
        JLabel l2 = new JLabel("Mat khau: ");
        username = new JTextField("truong");
        pass = new JPasswordField("654321");
        l1.setPreferredSize(new Dimension(100,50));
        l2.setPreferredSize(l1.getPreferredSize());
        username.setPreferredSize(new Dimension(150,20));
        pass.setPreferredSize(username.getPreferredSize());
        JPanel p1 = new JPanel();
        p1.add(banner);
        JPanel p2 = new JPanel();
        p2.add(l1);
        p2.add(username);
        JPanel p3 = new JPanel();
        p2.add(l2);
        p2.add(pass);
        JPanel p4 = new JPanel();
        JButton but1 = new JButton("Dang nhap");
        p4.add(but1);
        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAccount();
            }
        });

        add(p1);
        add(p2);
        add(p3);
        add(p4);
    }

    private int checkAccount() {
        try {
            TaiKhoanDTO tk = TaiKhoanDAO.getInstance().selectById(username.getText());
                if(tk == null) {
                    System.out.println("ten dang nhap sai");
                    return -1;
                }
                String mk = tk.getMatkhau();
                if(BCrypt.checkpw(String.copyValueOf(pass.getPassword()), mk)) {
                    System.out.println("Dang nhap thanh cong");
                    this.dispose();
                    MainFrame main = new MainFrame(tk);
                    main.setVisible(true);
                    return 1;
                }
                else System.out.println("sai mat khau");
         } catch(Exception e) {
            e.printStackTrace();
         }
         return 0;
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}
