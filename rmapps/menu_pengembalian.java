package rmapps;

import database.koneksidb;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import database.resultsettable;
import java.sql.SQLException;
import java.text.MessageFormat;

import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableModel;


public class menu_pengembalian extends javax.swing.JFrame {
     ResultSet rs;
     koneksidb con;
     String hak;
     String status1;
     /**
     * Creates new form menu_home
     */
    public menu_pengembalian() {
        con = new koneksidb(new database.parameter().url, 
              new database.parameter().USERNAME_DB, 
              new database.parameter().PASSWORD_DB);
     
        initComponents();
        loadMobil();
        loadTabel(); 
        setLocationRelativeTo(null);
        
        bt_kembalikanmobil.setEnabled(false);
        cb_nopol.requestFocus();
        //setExtendedState(MAXIMIZED_BOTH);       
    }
    
   
    
    private void loadTabel() {
        String namaKolom[] = {"id_transaksi", "peminjam", "nopol", 
                              "tgl_pinjaman", "tgl_kembali", "harga", 
                              "lama", "total"};
        rs = con.querySelect(namaKolom, "tb_transaksi");
        tabelpengembalian.setModel(new resultsettable(rs));
    }

    private void loadMobil() {  // mengambil kode nopol dari database (tb_transaksi) untuk value cb_nopol
        rs = con.querySelectAll("tb_transaksi");
        try {
            while (rs.next()) {
                cb_nopol.addItem(rs.getString("nopol"));
            }

        } catch (SQLException ex) {}
    }
    
    public void cekstatus() throws SQLException {
        rs = con.querySelectAll("tb_mobil", "nopol ='" + cb_nopol.getSelectedItem().toString() + "'");
        while (rs.next()) {
            status1 = rs.getString("status");
        }
        String update_status = "Tersedia";
        String kolom[] = {"status"};
        String isi[] = {update_status};
        con.queryUpdate("tb_mobil", kolom, isi, "nopol='" + cb_nopol.getSelectedItem().toString() + "'");
    }
    
    public void kembalikan_mobil() {
        if (JOptionPane.showConfirmDialog(this, "Yakin Mengembalikan Mobil ?",
            "Peringatan", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            con.queryDelete("tb_transaksi", "nopol='" + cb_nopol.getSelectedItem().toString() + "'");
            this.cb_nopol.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Sekarang Mobil Telah Tersedia!","Pengembalian Berhasil",
                    JOptionPane.INFORMATION_MESSAGE);
            
        }
    }

    private void clear() { 
        ShowNama.setText("");
        cb_nopol.setSelectedItem("");
        ShowHarga.setText("");
        ShowTglP.setText("");
        ShowTglK.setText("");
        ShowLama.setText("");
    }
    
    public void cetakTransaksi() {
        try {
            tabelpengembalian.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat("Data Transaksi"), null);
        } catch (PrinterException ex) {}
    }
    
    public void exportkeExcel(){
        FileWriter fileWriter;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("[B]export_output/excel[/B]"));
        int retrival = chooser.showSaveDialog(null);
        if(retrival==JFileChooser.APPROVE_OPTION){
            try{
            TableModel tModel = tabelpengembalian.getModel();
            fileWriter = new FileWriter(new File(chooser.getSelectedFile()+".xls"));
            
            //writeHeader
            for(int i=0; i < tModel.getRowCount(); i++){
                for(int j=0; j < tModel.getColumnCount(); j++){
                    fileWriter.write(tModel.getValueAt(i,j).toString() + "\t");
                }
            fileWriter.write("\n");
            } fileWriter.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }   //JOptionPane.showMessageDialog(null, e);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        bt_sewamobil = new javax.swing.JButton();
        bt_kembalikanmobil = new javax.swing.JButton();
        bt_dash = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cb_nopol = new javax.swing.JComboBox<>();
        ShowHarga = new javax.swing.JLabel();
        ShowTglP = new javax.swing.JLabel();
        ShowTglK = new javax.swing.JLabel();
        ShowLama = new javax.swing.JLabel();
        ShowNama = new javax.swing.JLabel();
        bt_kembalikan = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        bt_cetak = new javax.swing.JButton();
        bt_refresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelpengembalian = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        bt_logout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/return-car.png"))); // NOI18N
        jLabel1.setText("Rent APPs| Kembalikan Motor");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MENU", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        bt_sewamobil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lease.png"))); // NOI18N
        bt_sewamobil.setText(" Sewa Motor");
        bt_sewamobil.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_sewamobil.setBorderPainted(false);
        bt_sewamobil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sewamobilActionPerformed(evt);
            }
        });

        bt_kembalikanmobil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        bt_kembalikanmobil.setText(" Kembalikan Motor");
        bt_kembalikanmobil.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_kembalikanmobil.setBorderPainted(false);

        bt_dash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/motorbike 2 kecil.png"))); // NOI18N
        bt_dash.setText("Dashboard");
        bt_dash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_dashActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_sewamobil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_kembalikanmobil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_dash, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_dash, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_sewamobil, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_kembalikanmobil, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/MOTRRRRRRRRRRR.png"))); // NOI18N

        lb_user.setBackground(new java.awt.Color(204, 204, 204));
        lb_user.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Form Pengembalian Mobil", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        jLabel3.setText("Nama Peminjam");

        jLabel4.setText("No. Polisi");

        jLabel5.setText("Harga");

        jLabel6.setText("Tanggal Peminjaman");

        jLabel7.setText("Tanggal Kembali");

        jLabel8.setText("Lama Peminjaman");

        cb_nopol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_nopolMouseClicked(evt);
            }
        });

        ShowHarga.setText("ShowHarga");

        ShowTglP.setText("ShowTglP");

        ShowTglK.setText("ShowTglK");

        ShowLama.setText("ShowLama");

        ShowNama.setText("ShowNama");

        bt_kembalikan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/backcar.png"))); // NOI18N
        bt_kembalikan.setText("Kembalikan Motor");
        bt_kembalikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_kembalikanActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Hari");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)))
                    .addComponent(jLabel3))
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_kembalikan)
                    .addComponent(ShowHarga)
                    .addComponent(ShowTglP)
                    .addComponent(ShowTglK)
                    .addComponent(ShowNama)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ShowLama)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel9))
                    .addComponent(cb_nopol, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ShowNama))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_nopol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ShowHarga)
                        .addGap(18, 18, 18)
                        .addComponent(ShowTglP)
                        .addGap(18, 18, 18)
                        .addComponent(ShowTglK)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ShowLama)
                            .addComponent(jLabel9))))
                .addGap(19, 19, 19)
                .addComponent(bt_kembalikan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bt_cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/print.png"))); // NOI18N
        bt_cetak.setText("Cetak Transaksi");
        bt_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cetakActionPerformed(evt);
            }
        });

        bt_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dbrefresh.png"))); // NOI18N
        bt_refresh.setText("Refresh");
        bt_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_refreshActionPerformed(evt);
            }
        });

        tabelpengembalian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "Nama Peminjam", "No. Polisi", "Harga Sewa", "Tgl. Peminjamani", "Tgl. Pengembalian", "Lama Peminjaman", "Total"
            }
        ));
        tabelpengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpengembalianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelpengembalian);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel_exports.png"))); // NOI18N
        jButton1.setText("Export Excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bt_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout11111111.png"))); // NOI18N
        bt_logout.setText("Log Out");
        bt_logout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_logout.setBorderPainted(false);
        bt_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lb_user, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bt_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bt_refresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 45, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(bt_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bt_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(bt_cetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lb_user, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );

        jPanel3.getAccessibleContext().setAccessibleName("Form Pengembalian Motor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_logoutActionPerformed
        int jawab = JOptionPane.showConfirmDialog(this,"Yakin?","Log Out",JOptionPane.YES_NO_OPTION);
        switch(jawab){
            case JOptionPane.YES_OPTION:
                dispose();
                menu_login rm = new menu_login();
                rm.setVisible(true);
            break;
            case JOptionPane.NO_OPTION:
                bt_logout.requestFocus();
            break;
        }
                // TODO add your handling code here:
    }//GEN-LAST:event_bt_logoutActionPerformed

    private void bt_dashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_dashActionPerformed
     
       
    }//GEN-LAST:event_bt_dashActionPerformed

    private void tabelpengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpengembalianMouseClicked
        
        ShowNama.setText(String.valueOf(tabelpengembalian.getValueAt(tabelpengembalian.getSelectedRow(),1)));
        cb_nopol.setSelectedItem(String.valueOf(tabelpengembalian.getValueAt(tabelpengembalian.getSelectedRow(),2)));
        ShowHarga.setText(String.valueOf(tabelpengembalian.getValueAt(tabelpengembalian.getSelectedRow(),3)));
        ShowTglP.setText(String.valueOf(tabelpengembalian.getValueAt(tabelpengembalian.getSelectedRow(),4)));
        ShowTglK.setText(String.valueOf(tabelpengembalian.getValueAt(tabelpengembalian.getSelectedRow(),5)));
        ShowLama.setText(String.valueOf(tabelpengembalian.getValueAt(tabelpengembalian.getSelectedRow(),6)));
    }//GEN-LAST:event_tabelpengembalianMouseClicked

    private void bt_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_refreshActionPerformed
        loadTabel();
        clear();
        cb_nopol.requestFocus();
    }//GEN-LAST:event_bt_refreshActionPerformed

    private void bt_kembalikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_kembalikanActionPerformed
        try {
            kembalikan_mobil();
            cekstatus();
            loadTabel();
            clear();
        } catch (SQLException ex) {}
    }//GEN-LAST:event_bt_kembalikanActionPerformed

    private void bt_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cetakActionPerformed
        cetakTransaksi();
    }//GEN-LAST:event_bt_cetakActionPerformed

    private void bt_sewamobilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_sewamobilActionPerformed
        this.setVisible(false);
        
        menu_peminjam rm = new menu_peminjam();
        rm.setVisible(true);
    }//GEN-LAST:event_bt_sewamobilActionPerformed

    private void cb_nopolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_nopolMouseClicked
       String st = (String) cb_nopol.getSelectedItem();
        ResultSet rst = con.querySelectAll("tb_transaksi", "nopol='" + st + "'");
        try {
            while (rst.next()) {
                this.ShowNama.setText(rst.getString("peminjam"));
                this.ShowHarga.setText(rst.getString("harga"));
                this.ShowTglP.setText(rst.getString("tgl_pinjaman"));
                this.ShowTglK.setText(rst.getString("tgl_kembali"));
                this.ShowLama.setText(rst.getString("lama"));
            }
        } catch (SQLException ex) {}
    }//GEN-LAST:event_cb_nopolMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        exportkeExcel();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(menu_pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu_pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu_pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu_pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new menu_home().setVisible(true);
                new menu_pengembalian().show();
                
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ShowHarga;
    private javax.swing.JLabel ShowLama;
    private javax.swing.JLabel ShowNama;
    private javax.swing.JLabel ShowTglK;
    private javax.swing.JLabel ShowTglP;
    private javax.swing.JButton bt_cetak;
    private javax.swing.JButton bt_dash;
    private javax.swing.JButton bt_kembalikan;
    private javax.swing.JButton bt_kembalikanmobil;
    private javax.swing.JButton bt_logout;
    private javax.swing.JButton bt_refresh;
    private javax.swing.JButton bt_sewamobil;
    private javax.swing.JComboBox<String> cb_nopol;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static final javax.swing.JLabel lb_user = new javax.swing.JLabel();
    private javax.swing.JTable tabelpengembalian;
    // End of variables declaration//GEN-END:variables
}

