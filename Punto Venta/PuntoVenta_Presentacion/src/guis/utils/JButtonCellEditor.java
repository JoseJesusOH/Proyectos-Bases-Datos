package guis.utils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class JButtonCellEditor extends DefaultCellEditor {

    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    private ActionListener onClickListener;

    public JButtonCellEditor(JTextField txt, ActionListener onClickListener) {
        super(txt);
        this.onClickListener = onClickListener;
        btn = new JButton();
        btn.setOpaque(true);
        //WHEN BUTTON IS CLICKED
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fireEditingStopped();
            }
        });
    }

    //OVERRIDE A COUPLE OF METHODS
    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj,
            boolean selected, int row, int col) {

        //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
        lbl = (obj == null) ? "" : obj.toString();
        btn.setText(lbl);
        clicked = true;
        return btn;
    }

    //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            this.onClickListener.actionPerformed(
                new ActionEvent(lbl, ActionEvent.ACTION_PERFORMED, null)
            );
        }
        //SET IT TO FALSE NOW THAT ITS CLICKED
        clicked = false;
        return new String(lbl);
    }

    @Override
    public boolean stopCellEditing() {

        //SET CLICKED TO FALSE FIRST
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        // TODO Auto-generated method stub
        super.fireEditingStopped();
    }
}
