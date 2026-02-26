package model;
import javax.swing.JOptionPane;

public class Unidade extends Carta implements Acao {

	private int atk, def;
	private boolean estaViva, atacou;

	public Unidade(String nome, int atk, int def) {
		super(nome);
		this.atk = atk;
		this.def = def;
		this.atacou = false;
	}

	// Getters e Setters

	public boolean isAtacou() {
		return atacou;
	}

	public void setAtacou(boolean atacou) {
		this.atacou = atacou;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public boolean isEstaViva() {
		return estaViva;
	}

	public void setEstaViva(boolean estaViva) {
		this.estaViva = estaViva;
	}

	// Cria uma instancia unica de unidade
	@Override
	public Unidade clone() {
		Unidade copia = new Unidade(this.nome, this.atk, this.def);
		copia.setEstaViva(this.estaViva);
		copia.setAtacou(this.atacou);
		return copia;
	}

	public boolean atacar(Jogador dono, Jogador oponente, Unidade alvo) {
		//Verifica se a unidade ja atacou
	    if (this.isAtacou()) {
	        JOptionPane.showMessageDialog(null, "Essa unidade já atacou neste turno.");
	        return false;
	    }

	    int atkAtacante = this.getAtk();
	    int defAlvo = alvo.getDef();

	    if (defAlvo < atkAtacante) {
	        oponente.setVida(oponente.getVida() - (atkAtacante - defAlvo));
	        alvo.setEstaViva(false);
	    } else if (defAlvo > atkAtacante) {
	        this.setEstaViva(false);
	        dono.setVida(dono.getVida() - (defAlvo - atkAtacante));
	    } else {
	        this.setEstaViva(false);
	        alvo.setEstaViva(false);
	    }

	    this.setAtacou(true);
	    dono.getCampo().limparMortas();
	    oponente.getCampo().limparMortas();

	    return true;
	}		

	public boolean atacarDireto(Jogador dono, Jogador oponente) {
		//Verifica se a unidade ja atacou
		if (this.isAtacou()) {
	        JOptionPane.showMessageDialog(null, "Essa unidade já atacou neste turno.");
	        return false;
	    }
		//Subtrai a vida do oponente diretamente
		oponente.setVida(oponente.getVida() - this.atk);		
        JOptionPane.showMessageDialog(null, this.getNome() + " atacou diretamente " + oponente.getNome() + " causando " + this.getAtk() + " de dano!");
		setAtacou(true);
		return true;
	}

	@Override
	public boolean jogarCarta(Jogador dono, Jogador oponente) {
		// Deixa a condicao da unidade como viva
		setEstaViva(true);
		// Adiciona unidade no campo
		return dono.getCampo().adicionarUnidade(this.clone());
	}

	public boolean realizarAcao(Jogador dono, Jogador oponente, Unidade alvo) {
	    return atacar(dono, oponente, alvo);
	}

	@Override
	public String toString() {
		return "<html>" +
		           "<b><span style='color:gray;'>[UNIDADE]</span></b> - " +
		           "<span style='color:blue;'>" + getNome() + "</span> - " +
		           "[<span style='color:red;'>🗡️ ATK: " + atk + "</span>, " +
		           "<span style='color:green;'>🛡️ DEF: " + def + "</span>]" +
		           "</html>";
	}
}