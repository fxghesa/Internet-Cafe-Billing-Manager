/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ISP;
import com.mysql.jdbc.Driver;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Administrator
 */
public class tampilPasca extends javax.swing.JFrame {
    Statement st;
    ResultSet rs;
    Connection pascaKonek;
    /**
     * Creates new form tampilPasca
     */
    public tampilPasca() {
        initComponents();
        bacaPasca();
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
        pascaTable = new javax.swing.JTable();
        labelJumlah = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pascaTable.setFont(new java.awt.Font("Aharoni", 0, 18)); // NOI18N
        pascaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(pascaTable);

        labelJumlah.setFont(new java.awt.Font("Aharoni", 1, 18)); // NOI18N
        labelJumlah.setText("Jumlah Pelanggan : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    protected void getKonekPra() throws SQLException{
        try{
            String id, pass, driver, url;
            id = "root";
            pass = "";
            driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/coba";
            Class.forName(driver).newInstance();
            pascaKonek = DriverManager.getConnection(url,id,pass);
            if (pascaKonek==null){
                System.out.println("koneksi gagal");
            }else{
                System.err.println("koneksi berhasil");
            }
        }catch (Exception e){
            System.err.println(""+e.getMessage());
        }
    }
    private void bacaPasca(){
        int jumlah=0;
        Object header[]={"Email","Password","Nama","No Telp"};
        DefaultTableModel data = new DefaultTableModel(null, header);
        pascaTable.setModel(data);
        String sql = "select * from pascabayar";
        try {
            getKonekPra();
            st = pascaKonek.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                jumlah++;
                String kolom1 = rs.getString(1);
                String kolom2 = rs.getString(2);
                String kolom3 = rs.getString(3);
                String kolom4 = rs.getString(4);
                String kolom[] = {kolom1, kolom2, kolom3,kolom4};
                data.addRow(kolom);
            }
            labelJumlah.setText("jumlah pelanggan : "+jumlah);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tampilPasca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tampilPasca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tampilPasca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tampilPasca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-labelJumlah        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tampilPasca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelJumlah;
    private javax.swing.JTable pascaTable;
    // End of variables declaration//GEN-END:variables
}
