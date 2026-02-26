package model;
import java.util.List;
import javax.swing.JOptionPane;

public class Armamento extends Carta implements Acao {

	private int bonus;

	public Armamento(String nome) {
		super(nome);
	}

	public Armamento(String nome, int bonus) {
		super(nome);
		this.bonus = bonus;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	@Override
	public boolean jogarCarta(Jogador dono, Jogador oponente) {
		// Verifica equipamento do jogador
		if (!dono.isEquipamento()) {
			System.out.println("Você já atribuiu um armamento este turno.");
			return false;
		}

		// Se nao tem ninguem em campo, nao atribui
		if (dono.getCampo().getUnidadesAtivas().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Impossivel atribuir, o campo esta vazio");
			return false;
		} else {
			JOptionPane.showMessageDialog(null, "Qual a unidade alvo?");
			// Escolha da unidade em campo
			int escolha;
			try {
				escolha = Integer.parseInt(JOptionPane.showInputDialog("Digite o índice da unidade: "));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Entrada inválida.");
				return false;
			}
			// Verificacao da unidade no campo
			if (escolha < 0 || escolha >= dono.getCampo().getUnidadesAtivas().size()) {
				JOptionPane.showMessageDialog(null, "Índice inválido da unidade.");
				return false;
			}
			List<Unidade> unidades = dono.getCampo().getUnidadesAtivas();
			armar(unidades.get(escolha));
			System.out.println("\u001B[36m" + this.getNome() + " equipado em " + unidades.get(escolha).getNome()
					+ "! ATK agora: " + unidades.get(escolha).getAtk() + "\u001B[0m");
			return true;
		}
	}

	private void armar(Unidade alvo) {
		//Muda o ataque do alvo
		alvo.setAtk(alvo.getAtk() + bonus);
	}

	@Override
	public boolean realizarAcao(Jogador dono, Jogador oponente, Unidade alvo) {
		return jogarCarta(dono, oponente);
	}

	@Override
	public String toString() {
		return "<html>" +
		       "<b><span style='color:darkorange;'>[ARMAMENTO]</span></b> - " +
		       "<span style='color:blue;'>" + getNome() + "</span> - " +
		       "[<span style='color:purple;'>🔧 Bônus: " + bonus + "</span>]" +
		       "</html>";
	}
}