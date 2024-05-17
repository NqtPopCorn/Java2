package com.myjava.quanlycuahang.GUI;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import com.myjava.quanlycuahang.BUS.ChucNangBUS;
import com.myjava.quanlycuahang.BUS.Constant;
import com.myjava.quanlycuahang.BUS.TaiKhoanBUS;
import com.myjava.quanlycuahang.BUS.VaiTroBUS;
import com.myjava.quanlycuahang.BUS.VaiTro_ChucNangBUS;
import com.myjava.quanlycuahang.DAO.TaiKhoanDAO;
import com.myjava.quanlycuahang.DTO.TaiKhoanDTO;
import com.myjava.quanlycuahang.DTO.VaiTroDTO;
import com.myjava.quanlycuahang.DTO.VaiTro_ChucNangDTO;
import com.myjava.quanlycuahang.GUI.DemoQLTKPanel;

public class MainFrame extends JFrame {
    public static TaiKhoanDTO taikhoan;
    public static VaiTroDTO vaitro;
    public NutChucNang taikhoanBtn;
    public NutChucNang quyenBtn;
    public NutChucNang sanphanBtn;
    public ButtonGroup chucnangGroup;
    public JPanel mainPanel;
    public JPanel leftMenuPanel;
    public DemoPhanQuyenPanel qlQuyenPanel;
    public QLSanPhamPanel qlSanPhamPanel;
    public DemoQLTKPanel demoQLTKPanel;
    
    public MainFrame(TaiKhoanDTO tk) {
        super("Quản lý cửa hàng");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1400, 800);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setLocationRelativeTo(null);

        if (tk == null) {
            taikhoan = TaiKhoanBUS.getInstance().tim(Constant.TaiKhoan.TENDANGNHAP, "truong");
            //lay vai tro cua tai khoan
            vaitro = VaiTroBUS.getInstance().tim(VaiTroBUS.TENVAITRO, taikhoan.getTenVaiTro());
        }
        else {
            taikhoan = tk;
            //lay vai tro cua tai khoan
            vaitro = VaiTroBUS.getInstance().tim(VaiTroBUS.TENVAITRO, taikhoan.getTenVaiTro());
            //khoi tao xong thi hien thong bao chao mung
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(null, "Chào mừng " + taikhoan.getUsername() + " đến với hệ thống quản lý cửa hàng");
                }
            });
        }

        leftMenuPanel = new JPanel();
        leftMenuPanel.setLayout(new GridLayout(10, 1));
        leftMenuPanel.setPreferredSize(new Dimension(230, this.getHeight()));
        mainPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        taikhoanBtn = new NutChucNang("Quản lý tài khoản");
        quyenBtn = new NutChucNang("Quản lý quyền");
        sanphanBtn = new NutChucNang("Quản lý sản phẩm");
        chucnangGroup = new ButtonGroup();
        chucnangGroup.add(taikhoanBtn);
        chucnangGroup.add(quyenBtn);
        chucnangGroup.add(sanphanBtn);
        leftMenuPanel.add(taikhoanBtn);
        leftMenuPanel.add(quyenBtn);
        leftMenuPanel.add(sanphanBtn);

        demoQLTKPanel = new DemoQLTKPanel();
        mainPanel.add(demoQLTKPanel, "Quan ly tai khoan");
        qlQuyenPanel = new DemoPhanQuyenPanel();
        qlQuyenPanel.add(new JLabel("Quan ly quyen"));
        mainPanel.add(qlQuyenPanel, "Quan ly quyen");
        qlSanPhamPanel = new QLSanPhamPanel();
        qlSanPhamPanel.add(new JLabel("Quan ly san pham"));
        mainPanel.add(qlSanPhamPanel, "Quan ly san pham");
        taikhoanBtn.setSelected(true);
        cardLayout.show(mainPanel, "Quan ly tai khoan");

        taikhoanBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                demoQLTKPanel.updateComponents();
                cardLayout.show(mainPanel, "Quan ly tai khoan");
            }
        });
        quyenBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                qlQuyenPanel.updateComponents();
                cardLayout.show(mainPanel, "Quan ly quyen");

            }
        });
        sanphanBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Quan ly san pham");
            }
        });

        this.getContentPane().add(leftMenuPanel, BorderLayout.WEST);
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);

        // this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame(null);
    }   
}
