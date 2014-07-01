/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import com.toedter.calendar.JDateChooser;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.List;
import javax.persistence.NoResultException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import jpa.AgendamentoJpaController;
import jpa.EvolucaoJpaController;
import jpa.ProfissionalJpaController;
import jpa.PacienteJpaController;
import jpa.exceptions.NonexistentEntityException;
import model.Agendamento;
import model.Evolucao;
import model.Internacao;
import model.Profissional;
import model.Paciente;

/**
 *
 * @author Ciça
 */
public class guiEvolucao extends javax.swing.JInternalFrame {
    private Internacao  inter;

    /**
     * Creates new form guiEvolucao
     */
    Paciente paciente;
    Evolucao evolucao;
    public guiEvolucao() {
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

        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtNome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        bntExcluir1 = new javax.swing.JButton();
        bntCancelar1 = new javax.swing.JButton();
        btnBuscarPaciente = new javax.swing.JButton();
        bntEditar1 = new javax.swing.JButton();
        txtPront = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cboProfissional = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        bntGravar1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEvolucao = new javax.swing.JTextArea();
        dataInicio1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstCirurgias = new javax.swing.JList();
        txtHora = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        dataEvolucao = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Evolução Médica");
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

        jLabel15.setText("Nome:");

        txtNome.setEditable(false);

        jLabel7.setText("Profissional:");

        bntExcluir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Excluir.jpg"))); // NOI18N
        bntExcluir1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bntExcluir1.setPreferredSize(new java.awt.Dimension(68, 68));
        bntExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExcluir1ActionPerformed(evt);
            }
        });

        bntCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Cancelar.jpg"))); // NOI18N
        bntCancelar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bntCancelar1.setPreferredSize(new java.awt.Dimension(68, 68));
        bntCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCancelar1ActionPerformed(evt);
            }
        });

        btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Pesquisa.jpg"))); // NOI18N
        btnBuscarPaciente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPacienteActionPerformed(evt);
            }
        });

        bntEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Editar.jpg"))); // NOI18N
        bntEditar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bntEditar1.setMaximumSize(new java.awt.Dimension(59, 63));
        bntEditar1.setMinimumSize(new java.awt.Dimension(59, 63));
        bntEditar1.setPreferredSize(new java.awt.Dimension(68, 68));
        bntEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEditar1ActionPerformed(evt);
            }
        });

        txtPront.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProntActionPerformed(evt);
            }
        });

        jLabel1.setText("Prontuário:");

        cboProfissional.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboProfissionalItemStateChanged(evt);
            }
        });

        jLabel20.setText("Data:");

        bntGravar1.setForeground(new java.awt.Color(153, 153, 153));
        bntGravar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Gravar.jpg"))); // NOI18N
        bntGravar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bntGravar1.setPreferredSize(new java.awt.Dimension(68, 68));
        bntGravar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntGravar1ActionPerformed(evt);
            }
        });

        jLabel17.setText("Hora:");

        txtEvolucao.setColumns(20);
        txtEvolucao.setRows(5);
        jScrollPane1.setViewportView(txtEvolucao);

        jLabel2.setText("Cirurgia:");

        lstCirurgias.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstCirurgiasValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstCirurgias);

        try {
            txtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setText("Data Evolução");

        dataEvolucao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dataEvolucaoFocusLost(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(bntGravar1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(bntEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(bntCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(bntExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPront, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarPaciente)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dataInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNome))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dataEvolucao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jButton1))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(14, 14, 14)
                                .addComponent(cboProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscarPaciente)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPront, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dataInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dataEvolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntGravar1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExcluir1ActionPerformed
        // TODO add your handling code here:
        try {
            new EvolucaoJpaController().destroy(evolucao.getId());
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(null, "Evolucao não existe");

        }

        JOptionPane.showMessageDialog(null,"Evolucao do paciente. "+ evolucao.getPaciente().getNome().toString()+ " excluída");

       txtNome.setText("");
        txtHora.setText("");
        txtPront.setText("");
        dataInicio1.setDate(null);
        cboProfissional.setSelectedIndex(0);     
        lstCirurgias.setModel(new DefaultListModel());

    }//GEN-LAST:event_bntExcluir1ActionPerformed

    private void bntCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCancelar1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_bntCancelar1ActionPerformed

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed
        // TODO add your handling code here:
        
        paciente = new PacienteJpaController().getPaciente(txtPront.getText());
        txtNome.setText(paciente.getNome().toString());
        List l = new AgendamentoJpaController().getAgendamentoPac(paciente.getId().toString());
        lstCirurgias.setListData(l.toArray());
        
        //List evo = new EvolucaoJpaController().getListEvolucao(paciente);
        //evolucao = new EvolucaoJpaController().getListEvolucao(paciente);
        //        
            
//            if(evo.isEmpty()){
//                paciente = new PacienteJpaController().getPaciente(txtPront.getText());
//                txtPront.setText(paciente.getId().toString());
//                txtNome.setText(paciente.getNome().toString());
//                List l2 = new AgendamentoJpaController().getAgendamentoPac(txtPront.getText());
//                lstCirurgias.setListData(l2.toArray());
//                txtHora.setText("");
//                dataInicio1.setDate(null);
//                cboProfissional.setSelectedIndex(0);
//                txtEvolucao.setText("");
//            }
//            
//        try {
//            
//            if(!(evo.isEmpty())){
//                evolucao = new EvolucaoJpaController().getEvolucao(paciente); 
//                txtPront.setText(evolucao.getPaciente().getId().toString());
//                txtNome.setText(evolucao.getPaciente().getNome().toString());
//                List l3 = new AgendamentoJpaController().getAgendamentoPac(txtPront.getText());
//                lstCirurgias.setListData(l3.toArray());
//                cboProfissional.setSelectedItem(cboProfissional);
//                txtEvolucao.setText(evolucao.getEvolucao().toString());
//                
//            }
//        }
//        catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Paciente com este número de prontuário não encontrado");
//            txtNome.setText("");
//        txtHora.setText("");
//        txtPront.setText("");
//        dataInicio1.setDate(null);
//        cboProfissional.setSelectedIndex(0);
//        lstCirurgias.setModel(new DefaultListModel());
//        txtEvolucao.setText("");
//        
//        evolucao = new EvolucaoJpaController().getEvolucao(paciente); 
//                txtPront.setText(evolucao.getPaciente().getId().toString());
//                txtNome.setText(evolucao.getPaciente().getNome().toString());
//                List l3 = new AgendamentoJpaController().getAgendamentoPac(txtPront.getText());
//                lstCirurgias.setListData(l3.toArray());
//                cboProfissional.setSelectedItem(cboProfissional);
//                txtEvolucao.setText(evolucao.getEvolucao().toString());
//            return;
//        }
        
                
        

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void bntEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEditar1ActionPerformed
        // TODO add your handling code here:

        if (evolucao==null) return;

                
        evolucao.setProfissional((Profissional)cboProfissional.getSelectedItem());
        evolucao.setHora(txtHora.getText());
        evolucao.setDataEvolucao(dataInicio1.getCalendar());        
        evolucao.setEvolucao(txtEvolucao.getText());

        try {
            new EvolucaoJpaController().edit(evolucao);

        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(null, "Evoluçao deste Paciente não existe");
           txtNome.setText("");
        txtHora.setText("");
        txtPront.setText("");
        dataInicio1.setDate(null);
        cboProfissional.setSelectedIndex(0);
        lstCirurgias.setModel(new DefaultListModel());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " Procure o Administrador do Sistema");
        }

        JOptionPane.showMessageDialog(null,"Detalhes de Evolução Alterados");
        
        txtNome.setText("");
        txtHora.setText("");
        txtPront.setText("");
        dataInicio1.setDate(null);
        cboProfissional.setSelectedIndex(0);
        lstCirurgias.setModel(new DefaultListModel());

    }//GEN-LAST:event_bntEditar1ActionPerformed

    private void txtProntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProntActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProntActionPerformed

    private void cboProfissionalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboProfissionalItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboProfissionalItemStateChanged

    private void bntGravar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntGravar1ActionPerformed
        // TODO add your handling code here:
        Evolucao a = new Evolucao();
        paciente = new PacienteJpaController().getPaciente(txtPront.getText());
        //  a.setEspecialidade((Especialidade) cboEspecialidade.getSelectedItem());
        a.setProfissional((Profissional)cboProfissional.getSelectedItem());
        a.setHora(txtHora.getText());
        a.setDataEvolucao(dataInicio1.getCalendar());
        a.setPaciente(paciente);
        a.setEvolucao(txtEvolucao.getText());
        
        Agendamento ag  = (Agendamento)lstCirurgias.getSelectedValue();
        a.setAgendamento(ag);
        
//        evolucao = new EvolucaoJpaController().getEvolucao(paciente);
//        
//        if(evolucao!=null){
//        JOptionPane.showMessageDialog(null,"Já existe evolução para este Paciente.\nSe deseja prosseguir com a operação\nselecione o botão EDITAR.");
//            return;
//        }
//        else{        
        new EvolucaoJpaController().create(a);
        JOptionPane.showMessageDialog(null,"Evolução efetuada\n");
 //       }
        
        txtNome.setText("");
        txtHora.setText("");
        txtPront.setText("");
        dataInicio1.setDate(null);
        cboProfissional.setSelectedIndex(0);
        lstCirurgias.setModel(new DefaultListModel());
        txtEvolucao.setText("");

    }//GEN-LAST:event_bntGravar1ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        List l = new ProfissionalJpaController().findProfissionalEntities();
        
        cboProfissional.removeAllItems();
        for  (int i=0;i<l.size();i++) {
        cboProfissional.addItem(l.get(i));
    }
    }//GEN-LAST:event_formInternalFrameActivated

    private void lstCirurgiasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstCirurgiasValueChanged
        // TODO add your handling code here:
         
//         Agendamento a  = (Agendamento)lstCirurgias.getSelectedValue();
//         lstDatas.removeAll();
//         try{
//             List l = new EvolucaoJpaController().getListDataEvolucao(a);         
//         if(l.isEmpty()){
//             return;
//         }
//         
//         lstDatas.setListData(l.toArray());
//         }catch(NullPointerException n){
//             return;
//             
//         }
//         
        /*
        GregorianCalendar dataE = (GregorianCalendar)lstDatas.getSelectedValue();
        Evolucao e = new EvolucaoJpaController().getEvolucaoData(dataE);
        cboProfissional.setSelectedItem(e.getProfissional());
        txtEvolucao.setText(e.getEvolucao().toString());*/
        //
         
    }//GEN-LAST:event_lstCirurgiasValueChanged

    private void dataEvolucaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dataEvolucaoFocusLost
        // TODO add your handling code here:
//        Agendamento a  = (Agendamento)lstCirurgias.getSelectedValue();
//        
//        Evolucao e = new EvolucaoJpaController().getEvolucaoData(dataEvolucao.getCalendar(), a);
//        cboProfissional.setSelectedItem(e.getProfissional());
//        txtEvolucao.setText(e.getEvolucao().toString());
    }//GEN-LAST:event_dataEvolucaoFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Agendamento a  = (Agendamento)lstCirurgias.getSelectedValue();
        try{
        Evolucao e = new EvolucaoJpaController().getEvolucaoData(dataEvolucao, a);
        cboProfissional.setSelectedItem(e.getProfissional());
        txtEvolucao.setText(e.getEvolucao().toString());
        txtHora.setText(e.getHora());
        
            
        }catch(NullPointerException nex){
            JOptionPane.showMessageDialog(null, "Selecione uma Cirurgia \npara realizar a busca.");
        } catch (NoResultException x){
            JOptionPane.showMessageDialog(null, "Evoluçao deste Paciente \nnesta data não existe");
        txtNome.setText("");
        txtHora.setText("");
        txtPront.setText("");
        dataInicio1.setDate(null);
        dataEvolucao.setDate(null);
        cboProfissional.setSelectedIndex(0);
        lstCirurgias.setModel(new DefaultListModel());

        }
         
        
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCancelar1;
    private javax.swing.JButton bntEditar1;
    private javax.swing.JButton bntExcluir1;
    private javax.swing.JButton bntGravar1;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JComboBox cboProfissional;
    private com.toedter.calendar.JDateChooser dataEvolucao;
    private com.toedter.calendar.JDateChooser dataInicio1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList lstCirurgias;
    private javax.swing.JTextArea txtEvolucao;
    private javax.swing.JFormattedTextField txtHora;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPront;
    // End of variables declaration//GEN-END:variables
}