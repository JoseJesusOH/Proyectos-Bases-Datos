
package guis;

import entidades.Socio;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.ISociosDAO;

public class SociosFormSin extends javax.swing.JFrame {

    private final ISociosDAO sociosDAO;

    public SociosFormSin(ISociosDAO sociosDAO) {
        initComponents();
        this.sociosDAO = sociosDAO;
        this.llenarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelIDSocio = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelCurp = new javax.swing.JLabel();
        jTextFieldIDSocio = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldCurp = new javax.swing.JTextField();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        tablaSocios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSoc = new javax.swing.JTable();
        jButtonEditar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administracion de Socios");
        getContentPane().setLayout(null);

        jLabelIDSocio.setText("ID Socio");
        getContentPane().add(jLabelIDSocio);
        jLabelIDSocio.setBounds(10, 30, 150, 30);

        jLabelNombre.setText("Nombre");
        getContentPane().add(jLabelNombre);
        jLabelNombre.setBounds(10, 80, 130, 30);

        jLabelCurp.setText("CURP");
        getContentPane().add(jLabelCurp);
        jLabelCurp.setBounds(10, 140, 130, 30);
        getContentPane().add(jTextFieldIDSocio);
        jTextFieldIDSocio.setBounds(190, 30, 80, 30);
        getContentPane().add(jTextFieldNombre);
        jTextFieldNombre.setBounds(190, 90, 150, 30);
        getContentPane().add(jTextFieldCurp);
        jTextFieldCurp.setBounds(190, 140, 150, 30);

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonGuardar);
        jButtonGuardar.setBounds(100, 220, 71, 23);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCancelar);
        jButtonCancelar.setBounds(240, 220, 80, 23);

        tablaSoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Socio", "Nombre", "Curp"
            }
        ));
        jScrollPane1.setViewportView(tablaSoc);

        tablaSocios.add(jScrollPane1);

        getContentPane().add(tablaSocios);
        tablaSocios.setBounds(350, 10, 450, 390);

        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonEditar);
        jButtonEditar.setBounds(830, 50, 61, 23);

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonEliminar);
        jButtonEliminar.setBounds(830, 100, 69, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void guardar() {
        if (this.jTextFieldIDSocio.getText().isEmpty()) {
            this.agregar();
        } else {
            this.actualizar();
        }
    }
    
    private void actualizar() {
        Long idSocio = Long.parseLong(this.jTextFieldIDSocio.getText());
        String nombre = this.jTextFieldNombre.getText();
        String curp = this.jTextFieldCurp.getText();
        Socio socio = new Socio(idSocio, nombre, curp);
        boolean seActualizoSocio = this.sociosDAO.actualizar(socio);
        if (seActualizoSocio) {
            JOptionPane.showMessageDialog(this, "Se actualizo el socio",
                    "Informacion", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
            this.llenarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizo el socio",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregar() {
        if (this.jTextFieldCurp.getText().isEmpty() || this.jTextFieldNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Es necesario escribir antes de guardar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nombre = this.jTextFieldNombre.getText();
        String curp = this.jTextFieldCurp.getText();
        Socio socio = new Socio(nombre, curp);
        boolean seAgregoSocio = this.sociosDAO.agregar(socio);
        if (seAgregoSocio) {
            JOptionPane.showMessageDialog(this, "Se agrego el socio",
                    "Informacion", JOptionPane.INFORMATION_MESSAGE);
            this.limpiar();
            this.llenarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar el socio",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void editar() {
        Long idSocioEditar = this.getIdSociosSeleccionado();
        if (idSocioEditar == null) {
            JOptionPane.showMessageDialog(this, "Debes de seleccionar un socio para eliminarlo",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Socio socio = this.sociosDAO.consultar(idSocioEditar);
        if (socio != null) {
            this.llenarFormulario(socio);
        }
    }

    public void llenarFormulario(Socio socio) {
        jTextFieldIDSocio.setText(String.valueOf(socio.getIdSocio()));
        jTextFieldNombre.setText(socio.getNombre());
        jTextFieldCurp.setText(socio.getCurp());
    }

    private void limpiar() {
        this.jTextFieldCurp.setText("");
        this.jTextFieldIDSocio.setText("");
        this.jTextFieldNombre.setText("");
    }
    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        this.guardar();
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.limpiar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void eliminar() {
        Long idSocioEliminar = this.getIdSociosSeleccionado();
        if (idSocioEliminar == null) {
            JOptionPane.showMessageDialog(this, "Debes de seleccionar un socio para eliminarlo",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int opcionSelecionada = JOptionPane.showConfirmDialog(this, "Estas Seguro de eliminar el socio", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcionSelecionada == JOptionPane.NO_OPTION) {
            return;
        }
        boolean seEliminoSocio = this.sociosDAO.eliminar(idSocioEliminar);
        if (seEliminoSocio) {
            JOptionPane.showMessageDialog(this, "Se elimino el socio",
                    "Informacion", JOptionPane.INFORMATION_MESSAGE);
            this.llenarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el socio",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Long getIdSociosSeleccionado() {
        int indiceFilaSe = this.tablaSoc.getSelectedRow();
        if (indiceFilaSe != -1) {
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaSoc.getModel();
            int indiceColumna = 0;
            Long id = (Long) modeloTabla.getValueAt(indiceFilaSe, indiceColumna);
            return id;
        } else {
            return null;
        }
    }
    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        this.eliminar();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        this.editar();
    }//GEN-LAST:event_jButtonEditarActionPerformed
    private void llenarTabla() {
        List<Socio> listaSocios = this.sociosDAO.consultarTodos();
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tablaSoc.getModel();
        modeloTabla.setRowCount(0);
        listaSocios.forEach(socio -> {
            Object[] fila = new Object[3];
            fila[0] = socio.getIdSocio();
            fila[1] = socio.getNombre();
            fila[2] = socio.getCurp();
            modeloTabla.addRow(fila);
        }
        );

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabelCurp;
    private javax.swing.JLabel jLabelIDSocio;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldCurp;
    private javax.swing.JTextField jTextFieldIDSocio;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTable tablaSoc;
    private javax.swing.JPanel tablaSocios;
    // End of variables declaration//GEN-END:variables
}