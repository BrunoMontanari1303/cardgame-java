package model;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Campo {
	private int limiteUnidades = 2;
	private List<Unidade> unidadesAtivas;

	public Campo() {
		this.unidadesAtivas = new ArrayList<>();
	}

	public List<Unidade> getUnidadesAtivas() {
		return unidadesAtivas;
	}

	public int getLimiteUnidades() {
		return limiteUnidades;
	}

	// Adiciona unidades as ativas
	public boolean adicionarUnidade(Unidade unidade) {
		if (unidadesAtivas.size() >= limiteUnidades) {
			JOptionPane.showInternalMessageDialog(null, "Campo cheio, impossivel invocar!");
			return false;
		} else {
			unidadesAtivas.add(unidade);
			return true;
		}
	}

	// Remove as que morreram
	public void limparMortas() {
		unidadesAtivas.removeIf(u -> !u.isEstaViva());
	}
}