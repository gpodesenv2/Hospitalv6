/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import gui.Tabela;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import jpa.AgendamentoJpaController;
import jpa.ProfissionalJpaController;
import model.Agendamento;
import model.Profissional;
 

/**
 *
 * @author Manoela
 */
public class guiCalendarioCirurgia extends javax.swing.JInternalFrame {

    /**
     * Creates new form guiCalendarioCirurgia
     */
    public guiCalendarioCirurgia() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboMedico = new javax.swing.JComboBox();
        dataInicio = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCirurgia = new javax.swing.JTable();
        txtQtd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        bntBuscar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cirurgias Agendadas");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLabel1.setText("Data:");

        jLabel7.setText("Médico:");

        cboMedico.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMedicoItemStateChanged(evt);
            }
        });

        tableCirurgia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableCirurgia);

        jLabel2.setText("Total:");

        bntBuscar.setText("Buscar");
        bntBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel1))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cboMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(bntBuscar))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(42, 42, 42)
                                .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(bntBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboMedicoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMedicoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMedicoItemStateChanged

    private void bntBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBuscarActionPerformed
        AgendamentoJpaController aJpa = new AgendamentoJpaController();
        List list = aJpa.getAgendamentoData(dataInicio, (Profissional)cboMedico.getSelectedItem());
        
        preencherTabela(list);
       
        System.out.println("saiu do metodo peenchertabela");
//        Tabela();
//        System.out.println("saiu do método TABELA()");
//        
//        mostrarNaTabela();
//        System.out.println("Saiu do método MT");
    }//GEN-LAST:event_bntBuscarActionPerformed

    
    
    public void preencherTabela(List list) {
        
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{"Data","Hora","Paciente","Médico","Procedimento"};
        
        //AgendamentoJpaController aJpa = new AgendamentoJpaController();
               
                
        int total = 0; // Numero de posições preenchidas  
               for(int i=0;i<=list.toArray().length-1;i++){  
               if(!(list.toArray()[i]==0)){  
               total++;  
              }              
             }
        txtQtd.setText(Integer.toString(total));

        Object[] linha;
        
        try{
 
          if(list.isEmpty()){
                       JOptionPane.showMessageDialog(rootPane, "Não há cirurgias agendadas\nnesta data!");
                   }
           String dateStr;
           Date data;
               for(int i = 0; i<list.size(); i++){
                   linha =new Object[5];
                   SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                   data = ((Agendamento)list.get(i)).getDataInicio().getTime();
                   dateStr = dateformat.format(data);

                   linha[0]=(dateStr);
                   linha[1]=((Agendamento)list.get(i)).getHoraInicio();
                   linha[2]=((Agendamento)list.get(i)).getPaciente();
                   linha[3]=((Agendamento)list.get(i)).getProfissional();
                   linha[4]=((Agendamento)list.get(i)).getProcedimento();                   
                   dados.add(linha);  
                   System.out.println("dentro do for"+linha);
                   
               }

            
        }catch(OutOfMemoryError oe){
            JOptionPane.showMessageDialog(null, "JAVA HEAP SPACE!"+oe);
        }catch(ArrayIndexOutOfBoundsException ae){
            JOptionPane.showMessageDialog(null, "ArrayIndexOutOfBoundsException: 1"+ae);
        }catch(NullPointerException ne){
             JOptionPane.showMessageDialog(null, "vazio");

        }
        Tabela tab = new Tabela(dados, Colunas);
        tableCirurgia.setModel(tab);
        
        tableCirurgia.getColumnModel().getColumn(0).setPreferredWidth(85);
        tableCirurgia.getColumnModel().getColumn(0).setResizable(false);
        tableCirurgia.getColumnModel().getColumn(1).setPreferredWidth(80);
        tableCirurgia.getColumnModel().getColumn(1).setResizable(false);
        tableCirurgia.getColumnModel().getColumn(2).setPreferredWidth(200);
        tableCirurgia.getColumnModel().getColumn(2).setResizable(false);
        tableCirurgia.getColumnModel().getColumn(3).setPreferredWidth(150);
        tableCirurgia.getColumnModel().getColumn(3).setResizable(false);
        tableCirurgia.getColumnModel().getColumn(4).setPreferredWidth(120);
        tableCirurgia.getColumnModel().getColumn(4).setResizable(false);
        
        
        tableCirurgia.getTableHeader().setReorderingAllowed(false);
        tableCirurgia.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableCirurgia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    
    

    
    
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        List list = new ProfissionalJpaController().findProfissionalEntities();
        cboMedico.removeAllItems();
        for (int i = 0; i < list.size(); i++) {
            Profissional m = (Profissional)list.get(i);
            cboMedico.addItem(m);
        }
        
    }//GEN-LAST:event_formInternalFrameActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntBuscar;
    private javax.swing.JComboBox cboMedico;
    private com.toedter.calendar.JDateChooser dataInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCirurgia;
    private javax.swing.JTextField txtQtd;
    // End of variables declaration//GEN-END:variables
}
