/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
 
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
 
public class SearchPanel extends JFrame {
	private DefaultTableModel tableModel;
	private JTable table;
	private final int columnWithoutButton = 5;
	private Boolean [] enableBooleans = {true, false, false, true}; // Registre el estado habilitado / deshabilitado del botón
 
	public SearchPanel() {
		setLayout(new BorderLayout());
 
		setBounds(10,10,800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		this.setBackground(Color.white);
		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
 
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		panel.add(scrollPane);
 
		tableModel = new DefaultTableModel() {
			 // Establece si la columna se puede editar
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 5 || column == 6 || column == 7 || column == 8)
					return true;
				return false;
			}
		};
 
		String headName[] = { "Column1", "Column2", "Column3", "Column4",
				"Column5", "Column6", "Column7", "Column8", "Column9" };
		tableModel.setColumnIdentifiers(headName);
		table = new JTable(tableModel);
		table.getColumn("Column6").setCellRenderer(new ButtonRenderer());
		table.getColumn("Column6").setCellEditor(
				new ButtonEditor(new JCheckBox()));
 
		table.getColumn("Column7").setCellRenderer(new ButtonRenderer());
		table.getColumn("Column7").setCellEditor(
				new ButtonEditor(new JCheckBox()));
 
		table.getColumn("Column8").setCellRenderer(new ButtonRenderer());
		table.getColumn("Column8").setCellEditor(
				new ButtonEditor(new JCheckBox()));
 
		table.getColumn("Column9").setCellRenderer(new ButtonRenderer());
		table.getColumn("Column9").setCellEditor(
				new ButtonEditor(new JCheckBox()));
 
		table.setShowGrid(true);
 
		 // Establecer encabezados de columna para que no se muevan
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 
		scrollPane.setViewportView(table);
 
		 // Establezca el ancho de la columna, y el ancho de la columna se puede cambiar en consecuencia cuando se ajusta el Panel
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
 // table.setAutoResizeMode (JTable.AUTO_RESIZE_OFF); // Si se establece, el ancho de la columna siempre es fijo
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(55);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setPreferredWidth(70);
 
		 // establecer altura de línea
		table.setRowHeight(20);
		setVisible(true);
		
		InitValue();
	}
 
	private void InitValue() {
		for(int i=0;i<5;i++){
			Vector vector=new Vector();
			vector.addElement("1");
			vector.addElement("Open Close Msg");
			vector.addElement("do");
			vector.addElement("2");
			vector.addElement("2010-10-18 00:00:00");
			
			vector.addElement("Send");
			vector.addElement("Request");
			vector.addElement("Ack");
			vector.addElement("Emergency");
			
			tableModel.getDataVector().add(vector);
		}
	}
 
	public class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
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
				setBackground(UIManager.getColor("Button.background"));
			}
			setText((value == null) ? "" : value.toString());
			 // Caso 1: botón Activar / desactivar
			setEnabled(enableBooleans[column - columnWithoutButton]);
			 // Caso 2: cuando el botón está desactivado, el botón no se muestra y está en blanco
			// if(enableBooleans[column] - columnWithoutButton)
			// 	setEnabled(setEnabled(enableBooleans[column-columnWithoutButton]);
			// else
			// 	return null;
			return this;
		}
 
	}
 
	public class ButtonEditor extends DefaultCellEditor {
		protected JButton button;
 
		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
 
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ButtonClick();
				}
 
			});
		}
 
		@Override
		public Component getTableCellEditorComponent(JTable table1,
				Object value, boolean isSelected, int row, int column) {
			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}
 
			button.setText(table1.getValueAt(row, column).toString());
 
			 // Caso 1: botón Activar / desactivar
			button.setEnabled(enableBooleans[column - columnWithoutButton]);
			 // Caso 2: cuando el botón está desactivado, el botón no se muestra y está en blanco
			// if(enableBooleans[column] - columnWithoutButton)
			// 	button.setEnabled(setEnabled(enableBooleans[column-columnWithoutButton]);
			// else
			// 	return null;
			return button;
 
		}
 
		 // Si falta este método, el valor de Texto del botón no se obtendrá después de hacer clic en el botón y se mostrará falso.
		@Override
		public Object getCellEditorValue(){
			return button.getText();
		}
		
		protected void ButtonClick() {
			// TODO Auto-generated method stub
			System.out.println(table.getSelectedColumn() + " and  "
					+ table.getSelectedRow());
		}
	}
	
	
	public static void main(String args[]) {
		try {
			new SearchPanel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}