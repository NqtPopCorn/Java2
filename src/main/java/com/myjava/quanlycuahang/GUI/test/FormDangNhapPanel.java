package com.myjava.quanlycuahang.GUI.test;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import com.myjava.quanlycuahang.DAO.TaiKhoanDAO;
import com.myjava.quanlycuahang.DTO.TaiKhoanDTO;

public class FormDangNhapPanel extends JPanel {
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JButton btnDangNhap;

    
    public FormDangNhapPanel() {
        setLayout(new GridBagLayout());

        lblUsername = new JLabel("Username:");
        txtUsername = new JTextField();
        lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();
        btnDangNhap = new JButton("Đăng nhập");

        txtUsername.setPreferredSize(new Dimension(200, 30));
        txtPassword.setPreferredSize(new Dimension(200, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        add(lblUsername, gbc);

        gbc.gridy++;
        add(txtUsername, gbc);

        gbc.gridy++;
        add(lblPassword, gbc);

        gbc.gridy++;
        add(txtPassword, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnDangNhap, gbc);

        btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
    }

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            TaiKhoanDTO tk = TaiKhoanDAO.getInstance().selectById(txtUsername.getText());
            if (tk != null && BCrypt.checkpw(new String(txtPassword.getPassword()), tk.getMatkhau())) {
                System.out.println("Dang nhap thanh cong");
                JOptionPane.showMessageDialog(this, "Dang nhap thanh cong", "Thong bao", JOptionPane.PLAIN_MESSAGE);
                
            } else {
                System.out.println("Dang nhap that bai tai khoan khong ton tai hoac mat khau khong dung");
                JOptionPane.showMessageDialog(this, "Dang nhap that bai tai khoan khong ton tai hoac mat khau khong dung", "Thong bao", JOptionPane.ERROR_MESSAGE);
            }    
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

