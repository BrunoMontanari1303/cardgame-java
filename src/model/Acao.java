package model;
public interface Acao {
	// Criacao do metodo para possiveis outros tipos de cartas com outras acoes
	boolean realizarAcao(Jogador dono, Jogador oponente, Unidade alvo);
}
