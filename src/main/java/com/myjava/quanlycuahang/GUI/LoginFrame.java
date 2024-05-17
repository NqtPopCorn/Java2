package com.myjava.quanlycuahang.GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.mindrot.jbcrypt.BCrypt;

import com.myjava.quanlycuahang.DAO.TaiKhoanDAO;
import com.myjava.quanlycuahang.DTO.TaiKhoanDTO;

public class LoginFrame extends JFrame{
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JButton btnDangNhap;

    public LoginFrame() {
        super();
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

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

        panel.add(lblUsername, gbc);

        gbc.gridy++;
        panel.add(txtUsername, gbc);

        gbc.gridy++;
        panel.add(lblPassword, gbc);

        gbc.gridy++;
        panel.add(txtPassword, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnDangNhap, gbc);

        btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        this.getContentPane().add(panel);
        this.setVisible(true);
    }

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            TaiKhoanDTO tk = TaiKhoanDAO.getInstance().selectById(txtUsername.getText());
            if (tk != null && BCrypt.checkpw(new String(txtPassword.getPassword()), tk.getMatkhau())) {
                System.out.println("Dang nhap thanh cong");
                new MainFrame(tk);
                this.dispose();
                
            } else {
                System.out.println("Dang nhap that bai tai khoan khong ton tai hoac mat khau khong dung");
                JOptionPane.showMessageDialog(this, "Dang nhap that bai tai khoan khong ton tai hoac mat khau khong dung", "Thong bao", JOptionPane.ERROR_MESSAGE);
            }    
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
