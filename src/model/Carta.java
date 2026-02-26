package model;
	public abstract class Carta {
		protected String nome;
	
		public Carta(String nome) {
			this.nome = nome;
		}
	
		public abstract boolean jogarCarta(Jogador dono, Jogador oponente);
	
		public String getNome() {
			return nome;
		}
}
