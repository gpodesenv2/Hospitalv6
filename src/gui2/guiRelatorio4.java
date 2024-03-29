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
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import jpa.InternacaoJpaController;
import jpa.LocalizacaoJpaController;
import jpa.PacienteJpaController;
import model.Agendamento;
import model.Internacao;
import model.Localizacao;
import model.Paciente;

/**
 *
 * @author Manoela
 */
public class guiRelatorio4 extends javax.swing.JInternalFrame {

    /**
     * Creates new form guiRelatorio4
     */
    public guiRelatorio4() {
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

        datai = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        cboLocal = new javax.swing.JComboBox();
        cboTipo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtQtd = new javax.swing.JTextField();
        btnGerar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dataf = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableInternacao = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Relatório Internação");
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

        jLabel2.setText("Local:");

        cboLocal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocalItemStateChanged(evt);
            }
        });

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cirúrgico", "Clínico", "Pediátrico", "Obstétrico" }));
        cboTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTipoItemStateChanged(evt);
            }
        });

        jLabel1.setText("Tipo:");

        btnGerar.setText("Gerar");
        btnGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarActionPerformed(evt);
            }
        });

        jLabel3.setText("Quantidade:");

        jLabel4.setText("Período:");

        jLabel7.setText("a");

        tableInternacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tableInternacao);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cboLocal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(datai, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dataf, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                    .addComponent(btnGerar))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(28, 28, 28)
                            .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(cboLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dataf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(34, 34, 34))
                            .addComponent(jLabel2))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addComponent(btnGerar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboLocalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocalItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLocalItemStateChanged

    private void cboTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTipoItemStateChanged
        // TODO add your handling code here:
        String e = (String) cboTipo.getSelectedItem();

        if(e==null) return;

//        List list = new LocalizacaoJpaController().getListLocalizacao(e);
//        cboLocal.removeAllItems();
//        for (int i = 0; i < list.size(); i++) {
//            Localizacao m = (Localizacao)list.get(i);
//            cboLocal.addItem(m);
//        }
    }//GEN-LAST:event_cboTipoItemStateChanged

    private void btnGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarActionPerformed
        // TODO add your handling code here:
        

        try {
            
            List l = new InternacaoJpaController().getListPaciente((String)cboTipo.getSelectedItem(), (Localizacao) cboLocal.getSelectedItem(), datai, dataf);
            preencherTabela(l);
           
            int total = 0; // Numero de posições preenchidas
            for(int i=0;i<=l.toArray().length-1;i++){
                if(!(l.toArray()[i]==0)){
                    total++;
                }
                txtQtd.setText(Integer.toString(total));
            }
                       
            

            if(l.isEmpty()){
                JOptionPane.showMessageDialog(null,"Não foram realizadas internações neste local/ tipo de leito/ período");
                txtQtd.setText("");
            }

            

        } catch (Exception e) {

            return;
        }
    }//GEN-LAST:event_btnGerarActionPerformed

     public void preencherTabela(List list) {
        
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{"Nº Prontuário","Nome","Sala"};
        
        //AgendamentoJpaController aJpa = new AgendamentoJpaController();
               
                
        int total = 0; // Numero de posições preenchidas  
               for(int i=0;i<=list.toArray().length-1;i++){  
               if(!(list.toArray()[i]==0)){  
               total++;  
              }              
             }
        txtQtd.setText(Integer.toString(total));

        
        //List list = null;
        Object[] linha = new Object[5];
        
        try{
            
           
           //list=aJpa.findAgendamentoEntities();
          //list = aJpa.getAgendamentoData(dataInicio, (Profissional)cboMedico.getSelectedItem());
          if(list.isEmpty()){
                       JOptionPane.showMessageDialog(rootPane, "Não há Internações!");
                   }
           
               for(int i = 0; i<list.size(); i++){
                   
                   Paciente p = new PacienteJpaController().getPaciente(((Internacao)list.get(i)).getProntuario());

                   linha[0]=((Internacao)list.get(i)).getProntuario();                   
                   linha[1]=((Paciente)p).getNome();
                   linha[2]=((Internacao)list.get(i)).getLeito();
                                   
                   dados.add(linha);  
                   System.out.println("dentro do for"+linha);
                   
               }
               //dados.add(new Object[]{aJpa.rs.getDate("datainicio"), aJpa.rs.getString("horainicio"), aJpa.rs.getString("procedimento"), aJpa.rs.getInt("paciente_id"), aJpa.rs.getInt("profissional_id")});
//               dados.add(new Object[]{aJpa.rs.getInt("id"),aJpa.rs.getDate("datafim"),aJpa.rs.getDate("datainicio"), aJpa.rs.getString("horafim"),aJpa.rs.getString("horainicio"), aJpa.rs.getString("procedimento"),aJpa.rs.getInt("especialidade_id"), aJpa.rs.getInt("paciente_id"), aJpa.rs.getInt("profissional_id"), aJpa.rs.getInt("sala_id")});
             
            
        }catch(OutOfMemoryError oe){
            JOptionPane.showMessageDialog(null, "JAVA HEAP SPACE!"+oe);
        }catch(ArrayIndexOutOfBoundsException ae){
            JOptionPane.showMessageDialog(null, "ArrayIndexOutOfBoundsException: 1"+ae);
        }catch(NullPointerException ne){
             JOptionPane.showMessageDialog(null, "vazio");

        }
        Tabela tab = new Tabela(dados, Colunas);
        tableInternacao.setModel(tab);
        
        tableInternacao.getColumnModel().getColumn(0).setPreferredWidth(85);
        tableInternacao.getColumnModel().getColumn(0).setResizable(false);
        tableInternacao.getColumnModel().getColumn(1).setPreferredWidth(80);
        tableInternacao.getColumnModel().getColumn(1).setResizable(false);
        tableInternacao.getColumnModel().getColumn(2).setPreferredWidth(20);
        tableInternacao.getColumnModel().getColumn(2).setResizable(false);
        
        
        tableInternacao.getTableHeader().setReorderingAllowed(false);
        tableInternacao.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableInternacao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
     
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        List lLocal = new LocalizacaoJpaController().findLocalizacaoEntities();
        cboLocal.removeAllItems();
        for (int i = 0; i < lLocal.size(); i++) {
         cboLocal.addItem(lLocal.get(i));
        }
    }//GEN-LAST:event_formInternalFrameActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGerar;
    private javax.swing.JComboBox cboLocal;
    private javax.swing.JComboBox cboTipo;
    private com.toedter.calendar.JDateChooser dataf;
    private com.toedter.calendar.JDateChooser datai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableInternacao;
    private javax.swing.JTextField txtQtd;
    // End of variables declaration//GEN-END:variables
}
