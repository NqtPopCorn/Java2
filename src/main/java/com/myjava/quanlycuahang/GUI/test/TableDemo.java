package com.myjava.quanlycuahang.GUI.test;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;



public class TableDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Table Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Checkbox Column");
        model.addColumn("Text Column");
        model.addColumn("Text Column");
        model.addColumn("Text Column");
        model.addRow(new Object[]{true, "Row 1", "Row 1", "Row 1"});
        model.addRow(new Object[]{false, "Row 2", "Row 2", "Row 2"});
        model.addRow(new Object[]{true, "Row 3", "Row 3", "Row 3"});
        model.addRow(new Object[]{false, "Row 4", "Row 4", "Row 4"});
        model.addRow(new Object[]{true, "Row 5", "Row 5", "Row 5"});
        model.addRow(new Object[]{false, "Row 6", "Row 6", "Row 6"});

        // Create table with checkbox column
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setCellRenderer(table.getDefaultRenderer(Boolean.class));
        table.getColumnModel().getColumn(0).setCellEditor(table.getDefaultEditor(Boolean.class));
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                //in ra tat ca gia tri cua dong hien tai
                for (int i = 0; i < table.getColumnCount(); i++) {
                    System.out.println(table.getValueAt(table.getSelectedRow(), i));
                }
            }
        });

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add scroll pane to frame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Set frame size and visibility
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
