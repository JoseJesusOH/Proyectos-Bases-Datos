package Tabla;

import DAO.ProductoDAO;
import VO.ProductoVO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JButton;


public class Tabla_ProductoVO{

   ProductoDAO dao = null;

    public void visualizar_ProductoVO(JTable tabla){
        
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };    
        dt.addColumn("Codigo");
        dt.addColumn("Nombre");
        dt.addColumn("Precio");
        dt.addColumn("Marca");
        dt.addColumn("Modificar");
        dt.addColumn("Eliminar");
        
        JButton btn_modificar = new JButton("Modificar");
        btn_modificar.setName("m");
        JButton btn_eliminar = new JButton("Eliminar");
        btn_eliminar.setName("e");

        dao = new ProductoDAO();
        ProductoVO vo = new ProductoVO();
        ArrayList<ProductoVO> list = dao.Listar_ProductoVO();

        if(list.size() > 0){
            for(int i=0; i<list.size(); i++){
                Object fila[] = new Object[6];
                vo = list.get(i);
                fila[0] = vo.getCodigo();
                fila[1] = vo.getNombre();
                fila[2] = vo.getPrecio();
                fila[3] = vo.getMarca();
                fila[4] = btn_modificar;
                fila[5] = btn_eliminar;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);
        }
    }
}


