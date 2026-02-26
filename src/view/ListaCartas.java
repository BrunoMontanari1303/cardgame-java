package view;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.CartaDAO;
import model.Armamento;
import model.Carta;
import model.Unidade;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class ListaCartas extends JFrame {

	public ListaCartas() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage("imagens/icone.png"));
        atualizarTabela();
    }
	
	private void initComponents() {
        jScrollPane1 = new JScrollPane();
        tabelaCartas = new JTable();
        btnAtualizar = new JButton("Atualizar");
        btnEditar = new JButton("Editar Carta");
        btnExcluir = new JButton("Excluir Carta");
        btnPesquisar = new JButton("Pesquisar");
        campoBusca = new JTextField(15);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Cartas");

        tabelaCartas.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"Tipo", "Nome", "ATK/Bônus", "DEF"}
        ));
        jScrollPane1.setViewportView(tabelaCartas);

        btnAtualizar.addActionListener(e -> atualizarTabela());
        
        btnVoltar = new JButton("Voltar ao Menu");
        btnVoltar.addActionListener(evt -> {
            new MenuPrincipal().setVisible(true);
            this.dispose();
        });       
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
               
        btnExcluir.addActionListener(e -> excluirCarta());      
        btnEditar.addActionListener(e -> editarCarta());
        btnPesquisar.addActionListener(e -> pesquisarCarta());
        campoBusca.setColumns(10);
        
        
        
        
        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(btnVoltar)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnAtualizar)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnEditar)
        					.addGap(2)
        					.addComponent(btnExcluir)
        					.addGap(106))
        				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(campoBusca, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnPesquisar)
        					.addGap(12)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(campoBusca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnPesquisar))
        			.addGap(9)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnExcluir)
        				.addComponent(btnEditar)
        				.addComponent(btnAtualizar)
        				.addComponent(btnVoltar))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);
        pack();
    }
	
	//Atualiza a tabela
	public void atualizarTabela() {
		DefaultTableModel modelo = (DefaultTableModel) tabelaCartas.getModel();
	    modelo.setRowCount(0); // Limpa a tabela

	    CartaDAO dao = new CartaDAO();
	    java.util.ArrayList<Carta> cartasDoBanco = dao.listarCartas();

	    for (Carta c : cartasDoBanco) {
	        String tipo = c instanceof Unidade ? "Unidade" : "Armamento";
	        String nome = c.getNome();
	        String atkOuBonus = "";
	        String def = "";

	        if (c instanceof Unidade) {
	            Unidade u = (Unidade) c;
	            atkOuBonus = String.valueOf(u.getAtk());
	            def = String.valueOf(u.getDef());
	        } else if (c instanceof Armamento) {
	            Armamento a = (Armamento) c;
	            atkOuBonus = String.valueOf(a.getBonus());
	            def = "-";
	        }

	        modelo.addRow(new Object[]{tipo, nome, atkOuBonus, def});
	    }
	}
	
	//Exclui uma carta selecionada
	private void excluirCarta() {
		int linhaSelecionada = tabelaCartas.getSelectedRow();
	    if (linhaSelecionada == -1) {
	    	JOptionPane.showMessageDialog(this, "Selecione uma carta para excluir.");
	        return;
	    }

	    String nome = (String) tabelaCartas.getValueAt(linhaSelecionada, 1);

	    int confirmacao = JOptionPane.showConfirmDialog(
	    		this,
	            "Deseja realmente excluir a carta \"" + nome + "\"?",
	            "Confirmação",
	            JOptionPane.YES_NO_OPTION
	    );

	    if (confirmacao == JOptionPane.YES_OPTION) {
	    	CartaDAO dao = new CartaDAO();
	    	dao.deletarCarta(nome);
	    	atualizarTabela();
	        JOptionPane.showMessageDialog(this, "Carta \"" + nome + "\" excluída com sucesso.");
	    }
	}
	
	//Edita uma carta que foi selecionada da lista
	private void editarCarta() {
	    int cartaSelecionada = tabelaCartas.getSelectedRow();
	    if (cartaSelecionada == -1) {
	        JOptionPane.showMessageDialog(this, "Selecione uma carta para editar.");
	        return;
	    }

	    DefaultTableModel modelo = (DefaultTableModel) tabelaCartas.getModel();

	    String tipo = (String) modelo.getValueAt(cartaSelecionada, 0);
	    String nomeAntigo = (String) modelo.getValueAt(cartaSelecionada, 1);
	    String atkOuBonus = (String) modelo.getValueAt(cartaSelecionada, 2);
	    String def = (String) modelo.getValueAt(cartaSelecionada, 3);

	    String novoNome = JOptionPane.showInputDialog(this, "Novo nome:", nomeAntigo);
	    if (novoNome == null || novoNome.trim().isEmpty()) return;

	    if (tipo.equals("Unidade")) {
	        String novoAtk = JOptionPane.showInputDialog(this, "Novo ATK:", atkOuBonus);
	        String novoDef = JOptionPane.showInputDialog(this, "Nova DEF:", def);

	        try {
	            int atk = Integer.parseInt(novoAtk);
	            int defesa = Integer.parseInt(novoDef);

	            Unidade novaUnidade = new Unidade(novoNome, atk, defesa);
	            CartaDAO dao = new CartaDAO();
	            dao.atualizarCarta(nomeAntigo, novaUnidade);

	            JOptionPane.showMessageDialog(this, "Carta atualizada com sucesso!");
	            atualizarTabela();

	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(this, "ATK e DEF devem ser números válidos.");
	        }

	    } else if (tipo.equals("Armamento")) {
	        String novoBonus = JOptionPane.showInputDialog(this, "Novo bônus de ataque:", atkOuBonus);

	        try {
	            int bonus = Integer.parseInt(novoBonus);

	            Armamento novoArmamento = new Armamento(novoNome, bonus);
	            CartaDAO dao = new CartaDAO();
	            dao.atualizarCarta(nomeAntigo, novoArmamento);

	            JOptionPane.showMessageDialog(this, "Carta atualizada com sucesso!");
	            atualizarTabela();

	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(this, "O bônus deve ser um número válido.");
	        }
	    }
	}
	
	//Exibe a lista de cartas com nome semelhante
	private void pesquisarCarta() {
		String nomeBusca = campoBusca.getText().trim();

	    if (nomeBusca.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Digite um nome para buscar.");
	        return;
	    }

	    CartaDAO dao = new CartaDAO();
	    ArrayList<Carta> cartasEncontradas = dao.buscarCartas(nomeBusca);

	    DefaultTableModel modelo = (DefaultTableModel) tabelaCartas.getModel();
	    modelo.setRowCount(0); // Limpa a tabela

	    if (cartasEncontradas.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Nenhuma carta encontrada.");
	        return;
	    }

	    for (Carta c : cartasEncontradas) {
	        String tipo = c instanceof Unidade ? "Unidade" : "Armamento";
	        String nome = c.getNome();
	        String atkOuBonus = "";
	        String def = "";

	        if (c instanceof Unidade) {
	            Unidade u = (Unidade) c;
	            atkOuBonus = String.valueOf(u.getAtk());
	            def = String.valueOf(u.getDef());
	        } else if (c instanceof Armamento) {
	            Armamento a = (Armamento) c;
	            atkOuBonus = String.valueOf(a.getBonus());
	            def = "-";
	        }

	        modelo.addRow(new Object[]{tipo, nome, atkOuBonus, def});
	    }
	}
	
	private JScrollPane jScrollPane1;
    private JTable tabelaCartas;
    private JButton btnAtualizar;
    private JButton btnVoltar;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnPesquisar;
    private JTextField campoBusca;
}