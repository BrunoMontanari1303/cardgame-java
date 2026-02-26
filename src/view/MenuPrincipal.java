package view;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import model.Jogador;

public class MenuPrincipal extends JFrame {

	public MenuPrincipal() {
        setTitle("Menu Principal");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("imagens/icone.png"));

        //Cria a tela de criação de cartas
        JButton btnCriador = new JButton("Criador de Cartas");
        btnCriador.setBounds(140, 57, 200, 30);
        btnCriador.addActionListener(e -> {
            new CriadorCartas().setVisible(true);
            dispose();
        });
        getContentPane().add(btnCriador);

        //Exibe a lista de cartas criadas
        JButton btnLista = new JButton("Lista de Cartas");
        btnLista.setBounds(140, 99, 200, 30);
        btnLista.addActionListener(e -> {
            new ListaCartas().setVisible(true);
            dispose();
        });
        getContentPane().add(btnLista);
        
        //Abre a tela de montagem de baralho antes do inicio da partida
        JButton btnJogar = new JButton("Jogar!");
        btnJogar.addActionListener(e -> {      	
        	Jogador jogador1 = new Jogador(20);
            Jogador jogador2 = new Jogador(20);
            jogador1.setNome("Vermelho");
            jogador2.setNome("Azul");

            new MontagemBaralho().setVisible(true);
            dispose();
        });
        btnJogar.setBounds(140, 141, 200, 30);
        getContentPane().add(btnJogar);
    }

    public static void main(String[] args) {
    	try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    	SwingUtilities.invokeLater(() -> {   		
            new MenuPrincipal().setVisible(true);
        });
    }
}