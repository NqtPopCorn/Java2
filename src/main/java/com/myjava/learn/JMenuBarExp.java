package com.myjava.learn;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
 
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;
 
public class JMenuBarExp {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
 
    public JMenuBarExp() {
        prepareGUI();
    }
 
    public static void main(String[] args) {
        JMenuBarExp demo = new JMenuBarExp();
        demo.showMenuDemo();
    }
 
    private void prepareGUI() {
        mainFrame = new JFrame("Vi du JMenuItem trong Java Swing");
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new GridLayout(3, 1));
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        statusLabel.setSize(350, 100);
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
 
    private void showMenuDemo() {
        // Tao mot menu bar
        final JMenuBar menuBar = new JMenuBar(); // Tao cac menu
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        final JMenu aboutMenu = new JMenu("About");
        final JMenu linkMenu = new JMenu("Links");
 
        // tao cac item
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.setMnemonic(KeyEvent.VK_N);
        newMenuItem.setActionCommand("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.setActionCommand("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setActionCommand("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setActionCommand("Exit");
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.setActionCommand("Cut");
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.setActionCommand("Copy");
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.setActionCommand("Paste");
        MenuItemListener menuItemListener = new MenuItemListener();
        newMenuItem.addActionListener(menuItemListener);
        openMenuItem.addActionListener(menuItemListener);
        saveMenuItem.addActionListener(menuItemListener);
        exitMenuItem.addActionListener(menuItemListener);
        cutMenuItem.addActionListener(menuItemListener);
        copyMenuItem.addActionListener(menuItemListener);
        pasteMenuItem.addActionListener(menuItemListener);
        final JCheckBoxMenuItem showWindowMenu = new JCheckBoxMenuItem("Show About", true);
        showWindowMenu.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (showWindowMenu.getState()) {
                    menuBar.add(aboutMenu);
                } else {
                    menuBar.remove(aboutMenu);
                }
            }
        });
        final JRadioButtonMenuItem showLinksMenu = new JRadioButtonMenuItem("Show Links", true);
        showLinksMenu.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (menuBar.getMenu(3) != null) {
                    menuBar.remove(linkMenu);
                    mainFrame.repaint();
                } else {
                    menuBar.add(linkMenu);
                    mainFrame.repaint();
                }
            }
        }); // Them item toi cac menu
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(showWindowMenu);
        fileMenu.addSeparator();
        fileMenu.add(showLinksMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem); // Them menu toi menubar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(aboutMenu);
        menuBar.add(linkMenu); // Them menubar toi frame
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setVisible(true);
    }
 
    class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            statusLabel.setText(e.getActionCommand() + " JMenuItem clicked.");
        }
    }
}
