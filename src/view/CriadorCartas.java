package view;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.CartaDAO;
import model.Armamento;
import model.Unidade;

import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;


public class CriadorCartas extends javax.swing.JFrame {

    public CriadorCartas() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("imagens/icone.png"));
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoAtkOuBonus = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoDefesa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unidade", "Armamento" }));
        jComboBox1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jComboBox1ComponentResized(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Que tipo de carta deseja criar?");

        jLabel2.setText("Criação de Cartas");

        campoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeActionPerformed(evt);
            }
        });

        jLabel3.setText("Qual o nome da unidade?");

        campoAtkOuBonus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Qual o ATK da unidade?");

        campoDefesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel5.setText("Qual o DEF da unidade?");

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        
        JButton btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(evt -> {
            new MenuPrincipal().setVisible(true);
            this.dispose();
        });       

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
        					.addGap(18))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addGap(117))
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(160)
        					.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
        							.addGap(34)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(campoNome, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
        								.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        									.addGroup(layout.createSequentialGroup()
        										.addPreferredGap(ComponentPlacement.RELATED)
        										.addComponent(campoDefesa))
        									.addComponent(campoAtkOuBonus, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(102)
        							.addComponent(btnCancelar)
        							.addGap(18)
        							.addComponent(btnAdicionar)))))
        			.addContainerGap(121, Short.MAX_VALUE))
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(btnVoltar)
        			.addContainerGap(298, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
        				.addComponent(btnVoltar))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1)
        				.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(35)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel3)
        				.addComponent(campoNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(campoAtkOuBonus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel4))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(campoDefesa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel5))
        			.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnAdicionar)
        				.addComponent(btnCancelar))
        			.addGap(23))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
            String selectedOption = (String) jComboBox1.getSelectedItem();

        if ("Unidade".equals(selectedOption)) {
            // Mostrar campos para Unidade
            jLabel3.setText("Qual o nome da unidade?");
            jLabel4.setText("Qual o ATK da unidade?");
            jLabel5.setText("Qual o DEF da unidade?");
            campoDefesa.setVisible(true);
            jLabel5.setVisible(true);            
        } else if ("Armamento".equals(selectedOption)) {
            // Alterar campos para Armamento
            jLabel3.setText("Qual o nome do armamento?");
            jLabel4.setText("Qual o bonus do armamento?");
            
            // Ocultar o campo de defesa, pois não é relevante para Armamento
            campoDefesa.setVisible(false);
            jLabel5.setVisible(false);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void campoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeActionPerformed
    }//GEN-LAST:event_campoNomeActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed

    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jComboBox1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jComboBox1ComponentResized

    }//GEN-LAST:event_jComboBox1ComponentResized

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        campoNome.setText("");
        campoAtkOuBonus.setText("");
        campoDefesa.setText("");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
    	String nome = campoNome.getText(); // Nome da carta (Unidade ou Armamento)
        String tipoCarta = (String) jComboBox1.getSelectedItem();
        CartaDAO dao = new CartaDAO();

        if (tipoCarta.equals("Unidade")) {
            String atkText = campoAtkOuBonus.getText();
            String defText = campoDefesa.getText();

            if (nome.isEmpty() || atkText.isEmpty() || defText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos corretamente para uma unidade.");
                return;
            }

            try {
                int atk = Integer.parseInt(atkText);
                int def = Integer.parseInt(defText);
                Unidade novaUnidade = new Unidade(nome, atk, def);
                dao.inserirCarta(novaUnidade);  // só inserir no banco
                JLabel label = new JLabel(novaUnidade.toString());
                JOptionPane.showMessageDialog(null, label);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ATK e DEF devem ser números válidos.");
                return;
            }
        } else if (tipoCarta.equals("Armamento")) {
            String poderAtaqueText = campoAtkOuBonus.getText();

            if (nome.isEmpty() || poderAtaqueText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos corretamente para um armamento.");
                return;
            }
            try {
                int poderAtaque = Integer.parseInt(poderAtaqueText);
                Armamento novoArmamento = new Armamento(nome, poderAtaque);
                dao.inserirCarta(novoArmamento);
                JLabel label = new JLabel(novoArmamento.toString());
                JOptionPane.showMessageDialog(null, label);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Bônus do armamento deve ser um número válido.");
                return;
            }
        }

        campoNome.setText("");
        campoAtkOuBonus.setText("");
        campoDefesa.setText("");
	    }//GEN-LAST:event_btnAdicionarActionPerformed

    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JTextField campoNome;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField campoAtkOuBonus;
    private javax.swing.JTextField campoDefesa;    
}