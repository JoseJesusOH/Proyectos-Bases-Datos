package guis;

import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.EventoDAO;
import persistencia.IClienteDAO;
import persistencia.IConexionBD;
import persistencia.IEventoDAO;
import persistencia.ILugarDAO;
import persistencia.LugarDAO;
/**
 * Form de menu principal.
 * @author José Jesús Orozco Hernández ID; 00000229141
 */
public class MenuForm extends javax.swing.JFrame {
/**
 * Conexion 
 */
    private final IConexionBD conexion;
    /**
     * Conexion a clienteDAO
     */
    private final IClienteDAO clienteDAO;
/**
 * Conexion a lugarDAO
 */
    private final ILugarDAO lugarDAO;
    /**
     * Conexion a eventoDAO
     */
    private final IEventoDAO eventoDAO;

    /**
     * Creates new form MenuForm
     */
    public MenuForm() {
        initComponents();
        this.conexion = new ConexionBD();
        this.clienteDAO = new ClienteDAO(conexion);
        this.lugarDAO = new LugarDAO(conexion);
        this.eventoDAO = new EventoDAO(conexion);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonCliente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButtonLugar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButtonEvento = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal Eventos Sociales");
        setPreferredSize(new java.awt.Dimension(705, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("  Eventos Sociales ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(120, 30, 470, 80);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel1.setForeground(new java.awt.Color(51, 102, 255));
        jPanel1.setLayout(null);
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 700, 150);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel2.setForeground(new java.awt.Color(51, 102, 255));
        jPanel2.setLayout(null);

        jButtonCliente.setBackground(new java.awt.Color(102, 204, 255));
        jButtonCliente.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonCliente.setText("Cliente");
        jButtonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClienteActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonCliente);
        jButtonCliente.setBounds(40, 220, 120, 50);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cliente.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.add(jLabel2);
        jLabel2.setBounds(40, 40, 130, 130);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 150, 220, 320);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.setForeground(new java.awt.Color(51, 102, 255));
        jPanel3.setLayout(null);

        jButtonLugar.setBackground(new java.awt.Color(102, 204, 255));
        jButtonLugar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonLugar.setText("Lugar");
        jButtonLugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLugarActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonLugar);
        jButtonLugar.setBounds(70, 230, 110, 40);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/lugar.png"))); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel3.add(jLabel4);
        jLabel4.setBounds(60, 50, 123, 130);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(450, 150, 250, 320);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel4.setForeground(new java.awt.Color(51, 102, 255));
        jPanel4.setLayout(null);

        jButtonEvento.setBackground(new java.awt.Color(51, 204, 255));
        jButtonEvento.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButtonEvento.setText("Evento");
        jButtonEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEventoActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonEvento);
        jButtonEvento.setBounds(40, 230, 130, 40);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/evento.jpg"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.add(jLabel3);
        jLabel3.setBounds(30, 20, 173, 180);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(220, 150, 230, 320);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
/**
 * Evento del boton cliente, que crea un nueva instancia de ClientesForm.
 * @param evt Evento.
 */
    private void jButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClienteActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientesForm(clienteDAO).setVisible(true);
            }
        });
    }//GEN-LAST:event_jButtonClienteActionPerformed
/**
 * Evento del boton evento, que crea un nueva instancia de EventosForm.
 * @param evt Evento.
 */
    private void jButtonEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEventoActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EventosForm(eventoDAO, clienteDAO, lugarDAO).setVisible(true);
            }
        });
    }//GEN-LAST:event_jButtonEventoActionPerformed
/**
 * Evento del boton lugar, que crea un nueva instancia de LugaresForm.
 * @param evt Evento.
 */
    private void jButtonLugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLugarActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LugaresForm(lugarDAO).setVisible(true);
            }
        });
    }//GEN-LAST:event_jButtonLugarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCliente;
    private javax.swing.JButton jButtonEvento;
    private javax.swing.JButton jButtonLugar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}