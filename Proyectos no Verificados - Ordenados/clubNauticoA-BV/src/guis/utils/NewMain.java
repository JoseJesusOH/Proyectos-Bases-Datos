/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis.utils;

import java.awt.BorderLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;

public class NewMain {

  public static void main(final String args[]) {
    JFrame frame = new JFrame("Editable Tree");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JTree tree = new JTree();
    tree.setEditable(true);

    DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();

    TreeCellEditor comboEditor = new DefaultCellEditor(new JTextField());
    TreeCellEditor editor = new DefaultTreeCellEditor(tree, renderer, comboEditor);
    tree.setCellEditor(editor);

    JScrollPane scrollPane = new JScrollPane(tree);
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.setSize(300, 150);
    frame.setVisible(true);
  }
}