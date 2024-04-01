package com.myjava.quanlysinhvien.VIEW;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class TestMainFrame extends JFrame{
    private JPanel leftmenu;
    private JPanel content;
    private JButton homeButton;
    private JButton studentButton;
    private JButton classButton;
    private JButton subjectButton;
    private JButton markButton;

    public TestMainFrame() {
        super("Quan ly sinh vien");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        leftmenu = new JPanel();
        leftmenu.setLayout(new GridLayout(5,1));
        leftmenu.setSize(300, 400);
        content = new JPanel();
        homeButton = new JButton("Trang chu");
        studentButton = new JButton("Quan ly sinh vien");
        classButton = new JButton("Quan ly lop");
        subjectButton = new JButton("Quan ly mon hoc");
        markButton = new JButton("Quan ly diem");
        leftmenu.add(homeButton);
        leftmenu.add(studentButton);
        leftmenu.add(classButton);
        leftmenu.add(subjectButton);
        leftmenu.add(markButton);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();
                content.add(new AccountManagePanel());
                content.revalidate();
                content.repaint();
            }
        });
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();
                content.add(new QuanLyQuyenPanel());
                content.revalidate();
                content.repaint();
            }
        });
        add(leftmenu, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        TestMainFrame testMainFrame = new TestMainFrame();
        testMainFrame.setVisible(true);
    }
}
