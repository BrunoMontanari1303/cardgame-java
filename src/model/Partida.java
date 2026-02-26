package model;

import javax.swing.JOptionPane;

public class Partida {
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador atual;
    private Jogador oponente;
    private boolean emExecucao;
    public int turno = 1;

    public Partida(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.atual = jogador1;
        this.oponente = jogador2;
        this.emExecucao = true;
    }

    //Início da partida: embaralhar e comprar mão inicial
    public void iniciarPartida() {
        for (int i = 0; i < 3; i++) {
            jogador1.embaralhar();
            jogador2.embaralhar();
            jogador1.comprarCarta();
            jogador2.comprarCarta();
        }      
    } 

    //Troca de turno
    public boolean trocarTurno() {
    	Jogador temp = atual;
        atual = oponente;
        oponente = temp;

        atual.setConvocacao(true);
        atual.setEquipamento(true);

        atual.comprarCarta(); // Só compra

        if (verificarBaralhoVazio()) {
            return false;
        }
        atual.getCampo().getUnidadesAtivas().forEach(u -> u.setAtacou(false));
        turno++;
        return true;      
    }
    

	public boolean verificarBaralhoVazio() {
        if (atual.getBaralho().isEmpty()) {
            JOptionPane.showMessageDialog(null, atual.getNome() + " não possui mais cartas no baralho.\n"
                    + "Vitória de " + oponente.getNome() + "!");
            encerrarPartida();
            return true;
        }
        return false;
    }

    public String verificarVencedor() {
        if (jogador1.getVida() <= 0 || jogador1.getBaralho().isEmpty()) {
            emExecucao = false;
            return jogador2.getNome();
        } else if (jogador2.getVida() <= 0 || jogador2.getBaralho().isEmpty()) {
            emExecucao = false;
            return jogador1.getNome();
        }        
        return null;
    }

    // Encerra a partida
    public void encerrarPartida() {
        emExecucao = false;
    }    

    // Getters e Setters
    public Jogador getJogadorAtual() {
        return atual;
    }

    public Jogador getOponente() {
        return oponente;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public boolean isEmExecucao() {
        return emExecucao;
    }

    public void setEmExecucao(boolean emExecucao) {
        this.emExecucao = emExecucao;
    }
    
    public int getTurno() {
        return turno;
    }
}