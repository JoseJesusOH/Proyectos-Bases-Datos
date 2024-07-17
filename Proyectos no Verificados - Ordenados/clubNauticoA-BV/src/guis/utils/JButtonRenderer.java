package guis.utils;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class JButtonRenderer extends JButton implements TableCellRenderer {

    public JButtonRenderer(String text) {
//        this.setText(text);
        this.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
//            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        // Caso 1: botón Activar / desactivar
//        setEnabled(enableBooleans[column - columnWithoutButton]);
        // Caso 2: cuando el botón está desactivado, el botón no se muestra y está en blanco
        // if(enableBooleans[column] - columnWithoutButton)
        // 	setEnabled(setEnabled(enableBooleans[column-columnWithoutButton]);
        // else
        // 	return null;
        return this;
    }

//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object obj,
//            boolean isSelected, boolean hasFocus, int row, int column) {
//        setText((obj == null) ? "" : obj.toString());
//        return this;
//    }

}
