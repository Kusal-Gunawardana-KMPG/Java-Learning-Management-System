/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import static gui.Dashboard.classDetailTable;
import java.util.HashMap;
import javax.swing.JOptionPane;
import model.MySQL;

/**
 *
 * @author kguna
 */
public class editClass extends javax.swing.JFrame {

    /**
     * Creates new form editClass
     */
    public editClass(HashMap<String,String> x) {
        initComponents();
        editClassWindowSubjectName.setText(x.get("subject"));
        ecitClassWindowTeacherName.setText(x.get("teacher"));
        ecitClassWindowDescription.setText(x.get("description"));
        ecitClassWindowTimeSlot.setText(x.get("timeslot"));
        id.setText(x.get("classNo"));
        editClassWindowSubjectName.setEnabled(false);
        ecitClassWindowTeacherName.setEnabled(false);
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EditClassWindowPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ecitClassWindowDescription = new javax.swing.JTextArea();
        editClassButton = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        ecitClassWindowTimeSlot = new javax.swing.JTextField();
        ecitClassWindowTeacherName = new javax.swing.JTextField();
        editClassWindowSubjectName = new javax.swing.JTextField();
        id = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        EditClassWindowPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditClassWindowPanelMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Subject");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Teacher");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Description");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Time Slot");

        ecitClassWindowDescription.setColumns(20);
        ecitClassWindowDescription.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        ecitClassWindowDescription.setRows(5);
        jScrollPane1.setViewportView(ecitClassWindowDescription);

        editClassButton.setBackground(new java.awt.Color(0, 0, 102));
        editClassButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        editClassButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        editClassButton.setText("  Edit Class");
        editClassButton.setToolTipText("Edit Class");
        editClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editClassButtonActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(153, 204, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Edit Class");

        ecitClassWindowTimeSlot.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ecitClassWindowTimeSlot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ecitClassWindowTimeSlotActionPerformed(evt);
            }
        });

        ecitClassWindowTeacherName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ecitClassWindowTeacherName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ecitClassWindowTeacherNameActionPerformed(evt);
            }
        });

        editClassWindowSubjectName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout EditClassWindowPanelLayout = new javax.swing.GroupLayout(EditClassWindowPanel);
        EditClassWindowPanel.setLayout(EditClassWindowPanelLayout);
        EditClassWindowPanelLayout.setHorizontalGroup(
            EditClassWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditClassWindowPanelLayout.createSequentialGroup()
                .addGroup(EditClassWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditClassWindowPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(EditClassWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditClassWindowPanelLayout.createSequentialGroup()
                                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditClassWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(EditClassWindowPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(ecitClassWindowTeacherName))
                                .addGroup(EditClassWindowPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane1))
                                .addGroup(EditClassWindowPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(ecitClassWindowTimeSlot))
                                .addGroup(EditClassWindowPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(editClassWindowSubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(EditClassWindowPanelLayout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(editClassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        EditClassWindowPanelLayout.setVerticalGroup(
            EditClassWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditClassWindowPanelLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(EditClassWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(EditClassWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editClassWindowSubjectName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditClassWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ecitClassWindowTeacherName, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(EditClassWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditClassWindowPanelLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(EditClassWindowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ecitClassWindowTimeSlot, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(editClassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EditClassWindowPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EditClassWindowPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        Dashboard.editClassDashboardBtn.setVisible(false);
        Dashboard.addClassDashboardBtn.setVisible(false);
        Dashboard.editSubjectDashboardBtn.setVisible(false);
        Dashboard.addSubjectDashboardBtn.setVisible(false);
        
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        
        Dashboard.editClassDashboardBtn.setVisible(true);
        Dashboard.addClassDashboardBtn.setVisible(true);
        Dashboard.editSubjectDashboardBtn.setVisible(true);
        Dashboard.addSubjectDashboardBtn.setVisible(true);
        
    }//GEN-LAST:event_formWindowClosed

    private void EditClassWindowPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditClassWindowPanelMouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_EditClassWindowPanelMouseClicked

    private void ecitClassWindowTimeSlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ecitClassWindowTimeSlotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ecitClassWindowTimeSlotActionPerformed

    private void ecitClassWindowTeacherNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ecitClassWindowTeacherNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ecitClassWindowTeacherNameActionPerformed

    private void editClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editClassButtonActionPerformed
        // TODO add your handling code here:
        
        
        String description = ecitClassWindowDescription.getText();
        String timeSlot = ecitClassWindowTimeSlot.getText();
        String classId = id.getText();

         if (description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Add a Description", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (timeSlot.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Add a timeslot", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            JOptionPane.showMessageDialog(this, "Successfully Updated", "Updated", JOptionPane.INFORMATION_MESSAGE);

            try {

                MySQL.execute("UPDATE `class` SET `description`='"+description+"',`timeslot`='"+timeSlot+"' WHERE `clssno`='" + classId + "'  " );

            } catch (Exception e) {
                e.printStackTrace();
            }

            this.dispose();
            Dashboard.classDetailTable.setEnabled(true);

        }
        
    }//GEN-LAST:event_editClassButtonActionPerformed

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
            java.util.logging.Logger.getLogger(editClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new editClass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EditClassWindowPanel;
    private javax.swing.JTextArea ecitClassWindowDescription;
    private javax.swing.JTextField ecitClassWindowTeacherName;
    private javax.swing.JTextField ecitClassWindowTimeSlot;
    private javax.swing.JButton editClassButton;
    private javax.swing.JTextField editClassWindowSubjectName;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
