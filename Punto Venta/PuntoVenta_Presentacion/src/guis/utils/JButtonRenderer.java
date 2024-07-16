package guis.utils;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class JButtonRenderer extends JButton implements TableCellRenderer {

    public JButtonRenderer(String text) {
        this.setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((obj==null) ? "":obj.toString());
        return this;
    }
}
