/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package form;

import java.awt.HeadlessException;
import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;
import logic.ClientController;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class RegDialog extends javax.swing.JDialog {

    /**
     * Creates new form RegDialog
     */
    public RegDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        scaleImage();
        setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegister = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPasswordConfirm = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnRegister.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnRegister.setText("Registracija");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setText("Prijava");

        txtUsername.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtUsername.setPreferredSize(new java.awt.Dimension(5, 20));

        txtPassword.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtPassword.setMinimumSize(new java.awt.Dimension(5, 20));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setText("Korisnicko ime");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setText("Lozinka");

        txtLastName.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setText("Prezime");

        txtPasswordConfirm.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel9.setText("Potvrda lozinke");

        txtName.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel10.setText("Ime");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel10)
                .addGap(4, 4, 4)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(4, 4, 4)
                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        String username=txtUsername.getText().trim();
        String password=txtPassword.getText();
        String passwordConf=txtPasswordConfirm.getText();
        String name=txtName.getText().trim();
        String lastName=txtLastName.getText().trim();
        try {
            validateForm(username, password, passwordConf, name, lastName);
        } catch (Exception e) {
        }
        try {
            if (ClientController.getInstance().register(username, password, name, lastName)) {
                JOptionPane.showMessageDialog(null, "Uspešna registracija", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Korisnik sa takvim korisncikim imenom vec postoji!", "Neuspesno", JOptionPane.ERROR_MESSAGE);
                txtUsername.setText("");
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
            
        
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

   
 private void scaleImage() {
        ImageIcon icon = new ImageIcon("src/assets/loginPic1.jpg");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        jLabel8.setIcon(scaledIcon);
    }
 
 
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPasswordConfirm;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
    private void validateForm(String username, String password, String passwordConf, String name, String lastName) throws RuntimeException {
        if(username.isBlank() || password.isEmpty() || passwordConf.isEmpty() || name.isBlank() || lastName.isBlank())
            throw new RuntimeException("Popunite sva polja!");
        if(!password.equals(passwordConf)){
            txtPassword.setText("");
            txtPasswordConfirm.setText("");
            throw new RuntimeException("Lozinke se ne podudaraju!");
        }
    }

}
