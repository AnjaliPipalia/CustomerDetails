/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerdetails;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This is CustomerForm class that extends JFrame which on click operations
 *
 * @author arp226
 */
public class CustomerForm extends javax.swing.JFrame {

    private static CustomerForm instance;

    static CustomerForm getInstance() {

        if (instance == null) {
            instance = new CustomerForm();

        }
        return instance;
    }

    CustomerDB db;
    Customer c;
    JFrame jf = new JFrame();
    ArrayList<Customer> list;
    private CustomerManagerFrame manager;
    private String id;

    /**
     * Creates new form CustomerForm
     */
    private CustomerForm() {
        db = CustomerDB.getInstance();
        initComponents();
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEmail = new javax.swing.JLabel();
        lblFName = new javax.swing.JLabel();
        lblLName = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtFName = new javax.swing.JTextField();
        txtLName = new javax.swing.JTextField();
        btnAddNew = new javax.swing.JButton();
        btnUpdateNew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblEmail.setText("       Email:");

        lblFName.setText("First Name:");

        lblLName.setText("Last Name:");

        btnAddNew.setText("Add");
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });

        btnUpdateNew.setText("Update");
        btnUpdateNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail)
                    .addComponent(lblFName)
                    .addComponent(lblLName))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnUpdateNew, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                    .addComponent(txtEmail)
                    .addComponent(txtFName)
                    .addComponent(txtLName, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFName)
                    .addComponent(txtFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLName)
                    .addComponent(txtLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpdateNew, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(btnAddNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateNewActionPerformed
        // Validation of Email,fName,lName
        EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.validate(txtEmail.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Please enter valid email address");
            txtEmail.requestFocusInWindow();
            return;
        }
        if (!txtFName.getText().matches("^[a-zA-Z]*$")) {
            JOptionPane.showMessageDialog(null, "Please enter valid first name");
            txtFName.requestFocusInWindow();
            return;
        }
        if (!txtLName.getText().matches("^[a-zA-Z]*$")) {
            JOptionPane.showMessageDialog(null, "Please enter valid last name");
            txtLName.requestFocusInWindow();
            return;
        }

        manager.update(txtEmail.getText(), txtFName.getText(), txtLName.getText(), id);

    }//GEN-LAST:event_btnUpdateNewActionPerformed

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        // Validation of Email,fName,lName
        EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.validate(txtEmail.getText().trim()) || txtEmail.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Please enter valid email address");
            txtEmail.requestFocusInWindow();
            return;
        }
        if (!txtFName.getText().matches("^[a-zA-Z]*$") || txtFName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter valid first name");
            txtFName.requestFocusInWindow();
            return;
        }
        if (!txtLName.getText().matches("^[a-zA-Z]*$") || txtLName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter valid last name");
            txtLName.requestFocusInWindow();
            return;
        }
        manager.addCustomer(txtEmail.getText(), txtFName.getText(), txtLName.getText());

    }//GEN-LAST:event_btnAddNewActionPerformed

    void setEmail(String string) {
        txtEmail.setText(string);

    }

    void setFName(String string) {
        txtFName.setText(string);
    }

    void setLName(String string) {
        txtLName.setText(string);
    }

    void setManager(CustomerManagerFrame aThis) {
        this.manager = aThis;
    }

    void setID(String id) {
        this.id = id;
    }

    //on click add buttone it will fade out edit/Update button
    void fadeUpdate() {
        btnUpdateNew.setEnabled(false);
        btnAddNew.setEnabled(true);
    }

    // on click Update/Edit button is will fade out add button
    void fadeAdd() {
        btnAddNew.setEnabled(false);
        btnUpdateNew.setEnabled(true);
    }
//Validation of Email 

    private class EmailValidator {

        private Pattern pattern;
        private Matcher matcher;

        private static final String EMAIL_PATTERN
                = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        public EmailValidator() {
            pattern = Pattern.compile(EMAIL_PATTERN);
        }

        /**
         * Validate hex with regular expression
         *
         * @param hex hex for validation
         * @return true valid hex, false invalid hex
         */
        public boolean validate(final String hex) {

            matcher = pattern.matcher(hex);
            return matcher.matches();

        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnUpdateNew;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFName;
    private javax.swing.JLabel lblLName;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFName;
    private javax.swing.JTextField txtLName;
    // End of variables declaration//GEN-END:variables
}
