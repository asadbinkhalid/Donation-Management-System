/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import src.BLL;
import src.Project;

/**
 *
 * @author Asad Bin Khalid
 */
public class EditProjectTeam2 extends javax.swing.JFrame {
    public Project project;
    BLL bll;
    /**
     * Creates new form EditProjectTeam2
     * @param project
     */
    public EditProjectTeam2(Project project) {
        this.project = project;
        bll = BLL.getBllInstance();
        initComponents();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        vTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        removeButton = new javax.swing.JButton();
        replaceButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        vTextArea.setBackground(new java.awt.Color(255, 255, 204));
        vTextArea.setColumns(20);
        vTextArea.setRows(5);
        vTextArea.setBorder(null);
        for(int i=0;i<project.getTeamList().size(); i++){
            if(project.getTeamList().get(i)!=null){
                vTextArea.append(project.getTeamList().get(i).getName() + "\n");
            }
        }
        jScrollPane1.setViewportView(vTextArea);

        jLabel1.setText("Project Volunteers:");

        removeButton.setText("Remove");
        removeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeButtonMouseClicked(evt);
            }
        });

        replaceButton.setText("Replace");
        replaceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                replaceButtonMouseClicked(evt);
            }
        });

        addButton.setText("Add");
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
        });

        backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(addButton)
                                    .addGap(78, 78, 78)
                                    .addComponent(removeButton)
                                    .addGap(75, 75, 75)
                                    .addComponent(replaceButton)))))
                    .addComponent(backButton))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeButton)
                    .addComponent(replaceButton)
                    .addComponent(addButton))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        // TODO add your handling code here:
        AddVolunteer page = new AddVolunteer(project);
        page.start();
        this.setVisible(false);
    }//GEN-LAST:event_addButtonMouseClicked

    private void removeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeButtonMouseClicked
        // TODO add your handling code here:
        RemoveVolunteer page = new RemoveVolunteer(project);
        page.start();
        this.setVisible(false);
    }//GEN-LAST:event_removeButtonMouseClicked

    private void replaceButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_replaceButtonMouseClicked
        // TODO add your handling code here:
        ReplaceVolunteer page = new ReplaceVolunteer(project);
        page.start();
        this.setVisible(false);
        
    }//GEN-LAST:event_replaceButtonMouseClicked

    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseClicked
        // TODO add your handling code here:
        EditProjectTeam1 page = new EditProjectTeam1();
        page.start();
        this.setVisible(false);
    }//GEN-LAST:event_backButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public void start() {
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
            java.util.logging.Logger.getLogger(EditProjectTeam2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditProjectTeam2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditProjectTeam2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditProjectTeam2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditProjectTeam2(project).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton replaceButton;
    private javax.swing.JTextArea vTextArea;
    // End of variables declaration//GEN-END:variables
}