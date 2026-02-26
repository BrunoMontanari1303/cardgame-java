package model;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Jogador {
	private boolean convocacao = true, equipamento = true;
	private int vida;
	private String nome;
	private Queue<Carta> baralho = new LinkedList<>();
	private List<Carta> mao = new ArrayList<>();
	private Campo campo;
	private Partida partida;

	public Jogador(int vida) {
		this.campo = new Campo();
		this.vida = vida;
	}

	// Getters e Setters
	
	
	public Campo getCampo() {
		return campo;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public boolean isConvocacao() {
		return convocacao;
	}

	public void setConvocacao(boolean convocacao) {
		this.convocacao = convocacao;
	}

	public boolean isEquipamento() {
		return equipamento;
	}

	public void setEquipamento(boolean equipamento) {
		this.equipamento = equipamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nickname) {
		this.nome = nickname;
	}


	public List<Carta> getMao() {
		return mao;
	}

	public void setMao(List<Carta> mao) {
		this.mao = mao;
	}

	public Queue<Carta> getBaralho() {
		return baralho;
	}

	public void setBaralho(Queue<Carta> baralho) {
		this.baralho = baralho;
	}


	// Coloca cartas no baralho
	public void adicionaCarta(Carta carta) {
		if (baralho.size() == 20) {
			JOptionPane.showMessageDialog(null, "Tamanho maximo do baralho alcançado!");
		} else {
			baralho.add(carta);
		}
	}

	public void embaralhar() {
		List<Carta> embaralhado = new ArrayList<>(baralho);
		Collections.shuffle(embaralhado);
		baralho = new LinkedList<>(embaralhado);
	}

	public boolean comprarCarta() {
		// Adiciona cartas a mao se o baralho tem cartas
		if (baralho.isEmpty()) {
	        return false;
	    }
	    Carta cartaComprada = baralho.poll();
	    mao.add(cartaComprada);
	    JOptionPane.showMessageDialog(null, this.getNome() + " comprou: " + cartaComprada.getNome());
	    return true;
		
	}


	public boolean convocarUnidade(int indiceCarta, Jogador oponente) {
		// Verificacoes
		// Verifica a convocacao do jogador
		if (!this.isConvocacao()) {
			JOptionPane.showMessageDialog(null, "Você já convocou uma unidade neste turno.");
			return false;
		}
		// Verifica se a mao esta vazia
		if (this.getMao().isEmpty()) {
			System.out.println("Sua mão está vazia.");
			return false;
		}
		
		if (!convocacao) {
			return false;
		}
		Carta carta = mao.get(indiceCarta);	
		Unidade unidade = (Unidade) carta;
		if (unidade.jogarCarta(this, oponente)) {
			JOptionPane.showMessageDialog(null, "A carta " + unidade.getNome() + " foi colocada em campo");
			// Remove da mao
			mao.remove(indiceCarta);
			// Gasta a convocacao
			convocacao = false;
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "A unidade nao foi colocada em campo, a carta continua na mao");
			return false;
		}		
	}

	public boolean atribuirArmamento(int indiceCarta, Jogador oponente) {
		// Verificacoes
		if (indiceCarta < 0 || indiceCarta >= mao.size()) {
			JOptionPane.showMessageDialog(null, "Índice inválido da mão.");
			return false;
		}
		if (!equipamento) {
			JOptionPane.showMessageDialog(null, "Nao tem mais acoes de equipamento disponiveis!");
			return false;
		}

		Carta carta = mao.get(indiceCarta);
		// Se for armamento
		if (carta instanceof Armamento) {
			Armamento armamento = (Armamento) carta;
			if (armamento.jogarCarta(this, oponente)) {
				// Remove da mao
				mao.remove(indiceCarta);
				// Gasta o equipamento
				equipamento = false;
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "O armamento não foi utilizado. A carta continua na mao.");
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "A carta nao e um armamento");
			return false;
		}
	}
}