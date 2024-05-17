package com.myjava.learn;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.File;

public class FileChooserDemo extends JFrame {
    private JButton button;
    private JTextField textField;

    public FileChooserDemo() {
        button = new JButton("Browse");
        textField = new JTextField(30);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//only directories
                //fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//both files and directories
                //fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//only files
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    textField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        this.setLayout(new FlowLayout());
        this.add(button);
        this.add(textField);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FileChooserDemo();
            }
        });
    }
}