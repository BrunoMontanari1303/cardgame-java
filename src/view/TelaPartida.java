package view;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Carta;
import model.Jogador;
import model.Partida;
import model.Unidade;

public class TelaPartida extends JFrame {
	//Declaraçoes
	private Partida partida;
    
    private JLabel labelTurno;
    private JLabel labelVidaJogadores;
    private JPanel painelMao;
    private JPanel painelCampo;
    private JPanel painelCampoOponente;    
    private JButton btnConvocar;
    private JButton btnArmar;
    private JButton btnAtacar;
    private JButton btnEncerrar;
    private JButton btnVoltarMenu;
    
    //Cria a tela
    public TelaPartida(Jogador j1, Jogador j2) {   	
    	this.partida = new Partida(j1, j2);
    	partida.iniciarPartida();

        setTitle("Jogo de Cartas");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        setIconImage(Toolkit.getDefaultToolkit().getImage("imagens/icone.png"));

        inicializarComponentes();
        atualizarTela();
    }
    
    //Cria elementos
    private void inicializarComponentes() {
        labelTurno = new JLabel("Turno: " + partida.getTurno());
        labelTurno.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(labelTurno, BorderLayout.NORTH);
        
        labelVidaJogadores = new JLabel();
        labelVidaJogadores.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(labelVidaJogadores, BorderLayout.WEST);
                               
        painelMao = new JPanel();
        painelMao.setPreferredSize(new Dimension(400, 100));
        painelMao.setBorder(BorderFactory.createTitledBorder("Sua Mão"));       
        getContentPane().add(painelMao, BorderLayout.SOUTH);
        
      //Campos
        painelCampo = new JPanel(new FlowLayout());
        painelCampo.setBorder(BorderFactory.createTitledBorder("Seu Campo"));
        painelCampoOponente = new JPanel(new FlowLayout());
        painelCampoOponente.setBorder(BorderFactory.createTitledBorder("Campo do Oponente"));

        JPanel painelCampos = new JPanel(new GridLayout(2, 1));
        painelCampos.add(painelCampoOponente);
        painelCampos.add(painelCampo);
        getContentPane().add(painelCampos, BorderLayout.CENTER);
        
      //Mão
        painelMao = new JPanel(new FlowLayout());
        painelMao.setBorder(BorderFactory.createTitledBorder("Sua Mão"));
        getContentPane().add(painelMao, BorderLayout.SOUTH);
        
       //Botões
        JPanel painelBotoes = new JPanel(new GridLayout(5, 1, 10, 10));
        btnConvocar = new JButton("Convocar Unidade");
        btnArmar = new JButton("Atribuir Armamento");
        btnAtacar = new JButton("Atacar");
        btnEncerrar = new JButton("Encerrar Turno");
        btnVoltarMenu = new JButton("Voltar ao Menu");

        painelBotoes.add(btnConvocar);
        painelBotoes.add(btnArmar);
        painelBotoes.add(btnAtacar);
        painelBotoes.add(btnEncerrar);
        painelBotoes.add(btnVoltarMenu);

        getContentPane().add(painelBotoes, BorderLayout.EAST);
               
      //Ações dos botões
        btnConvocar.addActionListener(e -> {
            convocarUnidade();
        });

        btnArmar.addActionListener(e -> {
            atribuirArmamento();
        });

        btnAtacar.addActionListener(e -> {
            atacar();
        });

        btnEncerrar.addActionListener(e -> {
            encerrarTurno();
            atualizarTela();
        });
        
        btnVoltarMenu.addActionListener(e -> {
            new MenuPrincipal().setVisible(true);
            dispose();
        });
    }
              
    //Atualiza todos os componentes quando preciso
    private void atualizarTela() {
    	labelTurno.setText("Turno: " + partida.getTurno() + " - Jogador: " + partida.getJogadorAtual().getNome());
    	labelVidaJogadores.setText("Vida do " + partida.getJogadorAtual().getNome() + ": " + partida.getJogadorAtual().getVida() + "  "
    		+ "|  Vida do " + partida.getOponente().getNome() + ": " + partida.getOponente().getVida());

    	atualizarPainel(painelMao, partida.getJogadorAtual().getMao());
    	atualizarPainel(painelCampo, partida.getJogadorAtual().getCampo().getUnidadesAtivas());
    	atualizarPainel(painelCampoOponente, partida.getOponente().getCampo().getUnidadesAtivas());
    }
     
    //Atualiza os paineis montados
    private void atualizarPainel(JPanel painel, java.util.List<? extends Carta> cartas) {
    	painel.removeAll();
        for (Carta carta : cartas) {
            String texto = "<html><center>" + carta.getNome();

            if (carta instanceof Unidade unidade) {
                texto += "<br><img src='file:imagens/iconeatk.png' width='20' height='20'> " + unidade.getAtk();
                texto += " | <img src='file:imagens/iconedef.png' width='20' height='20'> " + unidade.getDef();
            } else if (carta instanceof model.Armamento arma) {
                texto += "<br><img src='file:imagens/iconebonus.png' width='20' height='20'> +" + arma.getBonus();
            }

            texto += "</center></html>";

            JButton btnCarta = new JButton(texto);
            btnCarta.setPreferredSize(new Dimension(100, 150));
            btnCarta.setToolTipText(carta.getNome());
            painel.add(btnCarta);
        }
        painel.revalidate();
        painel.repaint();
    }

    //Coloca uma unidade no campo
    private void convocarUnidade() {
    	    if (partida.getJogadorAtual().getMao().isEmpty()) {
    	        JOptionPane.showMessageDialog(this, "Não há cartas na mão.");
    	        return;
    	    }

    	 //Filtrar unidades na mão
    	    ArrayList<Integer> indicesDeUnidades = new ArrayList<>();
    	    ArrayList<String> nomesDeUnidades = new ArrayList<>();

    	    for (int i = 0; i < partida.getJogadorAtual().getMao().size(); i++) {
    	        Carta carta = partida.getJogadorAtual().getMao().get(i);
    	        if (carta instanceof Unidade) {
    	            indicesDeUnidades.add(i);
    	            nomesDeUnidades.add(carta.getNome());
    	        }
    	    }

    	    if (nomesDeUnidades.isEmpty()) {
    	        JOptionPane.showMessageDialog(this, "Não há unidades na mão para convocar.");
    	        return;
    	    }

    	    String escolha = (String) JOptionPane.showInputDialog(this, "Escolha a unidade para convocar:", "Convocar Unidade",
    	            JOptionPane.PLAIN_MESSAGE, null, nomesDeUnidades.toArray(), nomesDeUnidades.get(0));

    	    if (escolha != null) {
    	        int indiceEscolhido = nomesDeUnidades.indexOf(escolha);
    	        int indiceRealNaMao = indicesDeUnidades.get(indiceEscolhido);

    	        boolean sucesso = partida.getJogadorAtual().convocarUnidade(indiceRealNaMao, partida.getOponente());
    	        if (!sucesso) {
    	            JOptionPane.showMessageDialog(this, "Não foi possível convocar a unidade.");
    	        }
    	        atualizarTela();
    	    }   	
    }
    
    //Coloca um armamento em uma unidade em campo
    private void atribuirArmamento() {
    	if (partida.getJogadorAtual().getMao().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há cartas na mão.");
            return;
        }

        //Filtrar armamentos na mão
        ArrayList<Integer> indicesDeArmamentos = new ArrayList<>();
        ArrayList<String> nomesDeArmamentos = new ArrayList<>();

        for (int i = 0; i < partida.getJogadorAtual().getMao().size(); i++) {
            Carta carta = partida.getJogadorAtual().getMao().get(i);
            if (carta instanceof model.Armamento) {
                indicesDeArmamentos.add(i);
                nomesDeArmamentos.add(carta.getNome());
            }
        }

        if (nomesDeArmamentos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há armamentos na mão para atribuir.");
            return;
        }

        //Selecionar o armamento
        String escolhaArmamento = (String) JOptionPane.showInputDialog(
                this,
                "Escolha o armamento para atribuir:",
                "Atribuir Armamento",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nomesDeArmamentos.toArray(),
                nomesDeArmamentos.get(0)
        );

        if (escolhaArmamento == null) return; // Cancelado

        int indiceArmamentoEscolhido = nomesDeArmamentos.indexOf(escolhaArmamento);
        int indiceRealArmamento = indicesDeArmamentos.get(indiceArmamentoEscolhido);

        // Verificar unidades no campo
        if (partida.getJogadorAtual().getCampo().getUnidadesAtivas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há unidades no campo para equipar.");
            return;
        }

        // Listar unidades
        ArrayList<Unidade> unidadesNoCampo = new ArrayList<>(partida.getJogadorAtual().getCampo().getUnidadesAtivas());
        ArrayList<String> nomesUnidades = new ArrayList<>();
        for (Unidade u : unidadesNoCampo) {
            nomesUnidades.add(u.getNome() + " (ATK: " + u.getAtk() + ", DEF: " + u.getDef() + ")");
        }

        // Selecionar a unidade
        String escolhaUnidade = (String) JOptionPane.showInputDialog(
                this,
                "Escolha a unidade para equipar:",
                "Atribuir Armamento",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nomesUnidades.toArray(),
                nomesUnidades.get(0)
        );

        if (escolhaUnidade == null) return; // Cancelado

        int indiceUnidade = nomesUnidades.indexOf(escolhaUnidade);

        //Aplicar armamento manualmente
        model.Armamento armamento = (model.Armamento) partida.getJogadorAtual().getMao().get(indiceRealArmamento);
        Unidade unidadeEscolhida = unidadesNoCampo.get(indiceUnidade);

        //Aplicar efeito
        unidadeEscolhida.setAtk(unidadeEscolhida.getAtk() + armamento.getBonus());

        //Remover o armamento da mão
        partida.getJogadorAtual().getMao().remove(indiceRealArmamento);

        //Desabilitar o botão de equipamento nesse turno
        partida.getJogadorAtual().setEquipamento(false);

        JOptionPane.showMessageDialog(this,
                "Armamento \"" + armamento.getNome() + "\" equipado na unidade \"" + unidadeEscolhida.getNome() + "\" com sucesso!\n" +
                "Novo ATK da unidade: " + unidadeEscolhida.getAtk());

        atualizarTela();
     }
  
    
    private void atacar() {   	
    	Jogador atual = partida.getJogadorAtual();
        Jogador oponente = partida.getOponente();

        //Verificar se há unidades para atacar
        if (atual.getCampo().getUnidadesAtivas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Você não tem unidades para atacar.");
            return;
        }

        //Selecionar atacante
        ArrayList<Unidade> unidadesAtacantes = new ArrayList<>(atual.getCampo().getUnidadesAtivas());
        ArrayList<String> nomesAtacantes = new ArrayList<>();
        for (Unidade u : unidadesAtacantes) {
            nomesAtacantes.add(u.getNome() + " (ATK: " + u.getAtk() + ")" + (u.isAtacou() ? " [JÁ ATACOU]" : ""));
        }

        String escolhaAtacante = (String) JOptionPane.showInputDialog(
                this,
                "Escolha sua unidade atacante:",
                "Ataque",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nomesAtacantes.toArray(),
                nomesAtacantes.get(0)
        );

        if (escolhaAtacante == null) return;

        int indiceAtacante = nomesAtacantes.indexOf(escolhaAtacante);
        Unidade atacante = unidadesAtacantes.get(indiceAtacante);

        //Verificar se o oponente não tem unidades
        if (oponente.getCampo().getUnidadesAtivas().isEmpty()) {
            if (partida.getTurno() == 1) {
                JOptionPane.showMessageDialog(this, "Você não pode atacar direto no primeiro turno.");
                return;
            }
            //Atacar direto
            atacante.atacarDireto(atual, oponente);
            
            //Verifica se o ataque finalizou o oponente
            String vencedor = partida.verificarVencedor();
            if (vencedor != null) {
                JOptionPane.showMessageDialog(this, vencedor + " venceu a partida!");
                dispose();
                new MenuPrincipal().setVisible(true);
                return;
            }
            atualizarTela();
            return;
        }

        //Seleciona unidade alvo
        ArrayList<Unidade> unidadesOponentes = new ArrayList<>(oponente.getCampo().getUnidadesAtivas());
        ArrayList<String> nomesOponentes = new ArrayList<>();
        for (Unidade u : unidadesOponentes) {
            nomesOponentes.add(u.getNome() + " (DEF: " + u.getDef() + ")");
        }

        String escolhaOponente = (String) JOptionPane.showInputDialog(
                this,
                "Escolha a unidade inimiga para atacar:",
                "Ataque",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nomesOponentes.toArray(),
                nomesOponentes.get(0)
        );

        if (escolhaOponente == null) return;

        int indiceOponente = nomesOponentes.indexOf(escolhaOponente);
        Unidade alvo = unidadesOponentes.get(indiceOponente);

        //Realiza o ataque
        if (atacante.atacar(atual, oponente, alvo)) {
            JOptionPane.showMessageDialog(this, "Ataque realizado com sucesso!");
            //Verifica se o ataque finalizou o oponente
            String vencedor = partida.verificarVencedor();
            if (vencedor != null) {
                JOptionPane.showMessageDialog(this, vencedor + " venceu a partida!");
                dispose();
                new MenuPrincipal().setVisible(true);
                return;
            }
        }else {
            JOptionPane.showMessageDialog(this, "O ataque falhou ou não foi possível.");
        }

        atualizarTela();
    }
    
    //Troca o turno
    private void encerrarTurno() {
    	partida.trocarTurno();
    	if(!partida.isEmExecucao()) {
    		this.dispose();
    		new MenuPrincipal().setVisible(true);
    	}
        atualizarTela();
    }	
}