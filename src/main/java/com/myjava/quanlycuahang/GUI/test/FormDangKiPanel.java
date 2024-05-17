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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import com.myjava.quanlycuahang.UTIL.JDBCUtil;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class FormDangKiPanel extends JPanel {
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JLabel lblConfirmPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnDangKi;

    
    public FormDangKiPanel() {
        setLayout(new GridBagLayout());

        lblUsername = new JLabel("Username:");
        txtUsername = new JTextField();
        lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();
        lblConfirmPassword = new JLabel("Confirm Password:");
        txtConfirmPassword = new JPasswordField();
        btnDangKi = new JButton("Đăng kí");

        txtUsername.setPreferredSize(new Dimension(200, 30));
        txtPassword.setPreferredSize(new Dimension(200, 30));
        txtConfirmPassword.setPreferredSize(new Dimension(200, 30));

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
        add(lblConfirmPassword, gbc);

        gbc.gridy++;
        add(txtConfirmPassword, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnDangKi, gbc);

        btnDangKi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKiActionPerformed(evt);
            }
        });
    }

    private void btnDangKiActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan where tendangnhap = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtUsername.getText());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Tên đăng nhập đã tồn tại!");
                return;
            }
            else {
                if (!new String(txtPassword.getPassword()).equals(new String(txtConfirmPassword.getPassword()))) {
                    System.out.println("Mật khẩu không trùng khớp!");
                    return;
                }
                //TODO: chua thuc hien tai TaiKhoanDTO
                //...

                sql = "INSERT INTO taikhoan(tendangnhap, matkhau, manhomquyen, trangthai) VALUES(?, ?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, txtUsername.getText());
                ps.setString(2, BCrypt.hashpw(new String(txtPassword.getPassword()), BCrypt.gensalt()));
                ps.setInt(3, 10);
                ps.setInt(4, 1);
                int check = ps.executeUpdate();
                if (check > 0) {
                    System.out.println("Đăng kí thành công!");
                    this.setVisible(false);
                }
                else {
                    System.out.println("Đăng kí thất bại!");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

