
package customerdetails;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *This is a CustomerManagerFrame class that extends JFrame
 * @author arp226
 */
public class CustomerManagerFrame extends javax.swing.JFrame {

    CustomerDB db;
    private CustomerForm cForm;

    /**
     * Creates new form CustomerManagerFrame
     */
    public CustomerManagerFrame() {
        db = CustomerDB.getInstance();
        try {
            db.createTable();
            cForm = CustomerForm.getInstance();
            cForm.setManager(this);
            initComponents();
            showUsersInTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection failed");
        }

    }
    ArrayList<Customer> list;

    //Display Data in Jtable
    public void showUsersInTable() {
        list = db.getUserList();

        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        Object[] row = new Object[3];

        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getEmail();
            row[1] = list.get(i).getfName();
            row[2] = list.get(i).getlName();
            model.addRow(row);

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        customerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Email", "First Name", "Last Name"
            }
        ));
        jScrollPane2.setViewportView(customerTable);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int i = customerTable.getSelectedRow();
        if (i != -1) {
            Customer c = list.get(i);
            try {
                db.delete(c.getId());
                JOptionPane.showMessageDialog(null, "Data Deleted Successfully");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Failed to delete the customer", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
            }
            list.remove(i);
            showUsersInTable();
        } else {
            JOptionPane.showMessageDialog(null, "No row selected", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        cForm.setVisible(true);
        cForm.setEmail("");
        cForm.setFName("");
        cForm.setLName("");
        cForm.setID(null);
        cForm.fadeUpdate();

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int i = customerTable.getSelectedRow();
        if (i != -1) {
            cForm.setVisible(true);
            Customer c = list.get(i);
            cForm.setEmail(c.getEmail());
            cForm.setFName(c.getfName());
            cForm.setLName(c.getlName());
            cForm.setID(c.getId());
            cForm.fadeAdd();

        } else {
            JOptionPane.showMessageDialog(null, "No row selected", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnEditActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerManagerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JTable customerTable;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    //update the selected row in the Jtable
    void update(String textEmail, String textFName, String textLName, String id) {

        try {
            db.update(textEmail, textFName, textLName, id);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data failed to update");
        }
        JOptionPane.showMessageDialog(null, "Data updated Successfully");

        for (Customer customer : list) {
            if (customer.getId().equals(id)) {
                customer.setEmail(textEmail);
                customer.setfName(textFName);
                customer.setlName(textLName);
                cForm.setVisible(false);
                break;
            }
        }
        showUsersInTable();
    }
    // Add the user in the Jtable
    void addCustomer(String textEmail, String textFName, String textLName) {

        for (Customer customer : list) {
            if (customer.getEmail().equals(textEmail)) {
                JOptionPane.showMessageDialog(null, "Email address already exists");
                return;
            }
        }
        try {
            db.add(textEmail, textFName, textLName);
            JOptionPane.showMessageDialog(null, "Data Added Successfully");
            cForm.setVisible(false);
            cForm.setEmail("");
            cForm.setFName("");
            cForm.setLName("");
            cForm.setID(null);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to add the customer.Please check the email address", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
        }
        showUsersInTable();
    }
}
