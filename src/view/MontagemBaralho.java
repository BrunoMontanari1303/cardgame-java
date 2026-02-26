package view;

import controller.CartaDAO;
import model.Carta;
import model.Jogador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MontagemBaralho extends JFrame {

    private JTable tabelaCartasDisponiveis;
    private JTable tabelaBaralho;
    private DefaultTableModel modeloDisponiveis;
    private DefaultTableModel modeloBaralho;

    private ArrayList<Carta> cartasDisponiveis;
    private ArrayList<Carta> baralhoSelecionado = new ArrayList<>();
    private JLabel lblContador;
    private Jogador jogador1;
    private Jogador jogador2;
    private int etapaJogador = 1; // 1 = Jogador 1, 2 = Jogador 2

    public MontagemBaralho() {
        setTitle("Montagem do Baralho - Jogador 1");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setIconImage(Toolkit.getDefaultToolkit().getImage("imagens/icone.png"));

        inicializarComponentes();
        carregarCartas();
    }

    private void inicializarComponentes() {
    	modeloDisponiveis = new DefaultTableModel(new String[]{"Nome", "Tipo"}, 0);
        modeloBaralho = new DefaultTableModel(new String[]{"Nome", "Tipo"}, 0);

        tabelaCartasDisponiveis = new JTable(modeloDisponiveis);
        tabelaBaralho = new JTable(modeloBaralho);
        lblContador = new JLabel("Cartas no baralho: 0");

        JButton btnAdicionar = new JButton("Adicionar >>");
        JButton btnRemover = new JButton("<< Remover");
        JButton btnConfirmar = new JButton("Confirmar Baralho");
        JButton btnVoltar = new JButton("Voltar");

        // Painel esquerdo com cartas disponíveis
        JPanel painelEsquerdo = new JPanel(new BorderLayout());
        painelEsquerdo.add(new JScrollPane(tabelaCartasDisponiveis), BorderLayout.CENTER);

        // Painel direito com contador acima da tabela do baralho
        JPanel painelDireito = new JPanel(new BorderLayout());
        painelDireito.add(lblContador, BorderLayout.NORTH);
        painelDireito.add(new JScrollPane(tabelaBaralho), BorderLayout.CENTER);

        // Painel que contém os dois painéis lado a lado
        JPanel painelTabelas = new JPanel(new GridLayout(1, 2));
        painelTabelas.add(painelEsquerdo);
        painelTabelas.add(painelDireito);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnVoltar);

        add(painelTabelas, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        btnAdicionar.addActionListener(e -> adicionarCarta());
        btnRemover.addActionListener(e -> removerCarta());
        btnConfirmar.addActionListener(e -> confirmarBaralho());
        btnVoltar.addActionListener(e -> {
            new MenuPrincipal().setVisible(true);
            dispose();
        });
    }

    private void carregarCartas() {
        CartaDAO dao = new CartaDAO();
        cartasDisponiveis = dao.listarCartas();

        for (Carta c : cartasDisponiveis) {
            String tipo = (c.getClass().getSimpleName());
            modeloDisponiveis.addRow(new Object[]{c.getNome(), tipo});
        }
    }

    private void adicionarCarta() {
    	int cartaSelecionada = tabelaCartasDisponiveis.getSelectedRow();
        if (cartaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma carta para adicionar.");
            return;
        }

        String nomeCartaSelecionada = (String) modeloDisponiveis.getValueAt(cartaSelecionada, 0);

        // Verifica quantas cartas com mesmo nome já estão no baralho
        int count = 0;
        for (Carta c : baralhoSelecionado) {
            if (c.getNome().equalsIgnoreCase(nomeCartaSelecionada)) {
                count++;
            }
        }

        if (count >= 2) {
            JOptionPane.showMessageDialog(this, "Não é permitido ter mais que 2 cartas com o mesmo nome no baralho.");
            return;
        }

        // Acha o objeto Carta da lista de disponíveis
        Carta cartaParaAdicionar = null;
        for (Carta c : cartasDisponiveis) {
            if (c.getNome().equalsIgnoreCase(nomeCartaSelecionada)) {
                cartaParaAdicionar = c;
                break;
            }
        }
        if (cartaParaAdicionar == null) {
            JOptionPane.showMessageDialog(this, "Carta não encontrada.");
            return;
        }

        // Adiciona na lista e na tabela
        baralhoSelecionado.add(cartaParaAdicionar);
        String tipoCarta = (String) modeloDisponiveis.getValueAt(cartaSelecionada, 1);
        modeloBaralho.addRow(new Object[]{nomeCartaSelecionada, tipoCarta});
        atualizarContador();
    }
    
    //Tira uma carta selecionada do baralho
    private void removerCarta() {
        int linha = tabelaBaralho.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma carta para remover.");
            return;
        }

        baralhoSelecionado.remove(linha);
        modeloBaralho.removeRow(linha);
        atualizarContador();
    }
    
    //Confirma o baralho escolhido
    private void confirmarBaralho() {
    	if (baralhoSelecionado.size() < 10) {
            JOptionPane.showMessageDialog(this, "O baralho deve ter no mínimo 10 cartas.");
            return;
        }
        
        if (baralhoSelecionado.size() > 20) {
            JOptionPane.showMessageDialog(this, "O baralho deve ter no máximo 20 cartas.");
            return;
        }

        if (etapaJogador == 1) {
            //Salvar baralho do Jogador 1
            jogador1 = new Jogador(10);
            jogador1.setNome("Jogador 1");
            jogador1.getBaralho().addAll(baralhoSelecionado);

            limparSelecao();

            etapaJogador = 2;
            setTitle("Montagem do Baralho - Jogador 2");
            JOptionPane.showMessageDialog(this, "Jogador 2, monte seu baralho.");

        } else {
            //Salvar baralho do Jogador 2 e iniciar a partida
            jogador2 = new Jogador(10);
            jogador2.setNome("Jogador 2");
            jogador2.getBaralho().addAll(baralhoSelecionado);

            model.Partida partida = new model.Partida(jogador1, jogador2);
            jogador1.setPartida(partida);
            jogador2.setPartida(partida);

            TelaPartida tela = new TelaPartida(jogador1, jogador2);
            tela.setVisible(true);
            dispose();
        }
    }

    //Reseta todas as cartas escolhidas
    private void limparSelecao() {
        modeloBaralho.setRowCount(0);
        baralhoSelecionado.clear();
    }
    
    //Incrementa a contagem de cartas dentro de um baralho
    private void atualizarContador() {
    	int total = modeloBaralho.getRowCount();
        lblContador.setText("Cartas no baralho: " + total);
    }
}