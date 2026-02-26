package controller;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Armamento;
import model.Carta;
import model.Unidade;

public class CartaDAO {

    //Listar cartas
    public ArrayList<Carta> listarCartas() {
        ArrayList<Carta> cartas = new ArrayList<>();
        String sql = "SELECT * FROM cartas";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");

                if (tipo.equalsIgnoreCase("Unidade")) {
                    Unidade unidade = new Unidade(
                            rs.getString("nome"),
                            rs.getInt("atk"),
                            rs.getInt("def")
                    );
                    cartas.add(unidade);

                } else if (tipo.equalsIgnoreCase("Armamento")) {
                    Armamento arm = new Armamento(
                            rs.getString("nome"),
                            rs.getInt("bonus")
                    );
                    cartas.add(arm);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartas;
    }

    //Inserir carta
    public void inserirCarta(Carta carta) {
        String sql = "INSERT INTO cartas (nome, tipo, atk, def, bonus) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carta.getNome());

            if (carta instanceof Unidade) {
                Unidade u = (Unidade) carta;
                stmt.setString(2, "Unidade");
                stmt.setInt(3, u.getAtk());
                stmt.setInt(4, u.getDef());
                stmt.setNull(5, Types.INTEGER);
            } else if (carta instanceof Armamento) {
                Armamento a = (Armamento) carta;
                stmt.setString(2, "Armamento");
                stmt.setNull(3, Types.INTEGER);
                stmt.setNull(4, Types.INTEGER);
                stmt.setInt(5, a.getBonus());
            } else {
                stmt.setString(2, "Outro");
                stmt.setNull(3, Types.INTEGER);
                stmt.setNull(4, Types.INTEGER);
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.executeUpdate();       

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Atualizar carta
    public void atualizarCarta(String nomeAntigo, Carta novaCarta) {
        String sql = "UPDATE cartas SET nome=?, tipo=?, atk=?, def=?, bonus=? WHERE nome=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novaCarta.getNome());

            if (novaCarta instanceof Unidade) {
                Unidade u = (Unidade) novaCarta;
                stmt.setString(2, "Unidade");
                stmt.setInt(3, u.getAtk());
                stmt.setInt(4, u.getDef());
                stmt.setNull(5, Types.INTEGER);
            } else if (novaCarta instanceof Armamento) {
                Armamento a = (Armamento) novaCarta;
                stmt.setString(2, "Armamento");
                stmt.setNull(3, Types.INTEGER);
                stmt.setNull(4, Types.INTEGER);
                stmt.setInt(5, a.getBonus());
            } else {
                stmt.setString(2, "Outro");
                stmt.setNull(3, Types.INTEGER);
                stmt.setNull(4, Types.INTEGER);
                stmt.setNull(5, Types.INTEGER);
            }

            stmt.setString(6, nomeAntigo);

            int rows = stmt.executeUpdate();
            if (rows > 0) {           	
            } else {
            	JOptionPane.showInternalMessageDialog(null, "Carta não encontrada.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Deletar carta
    public void deletarCarta(String nome) {
        String sql = "DELETE FROM cartas WHERE nome = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
            } else {
            	JOptionPane.showInternalMessageDialog(null, "Carta não encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Buscar cartas com nome parecido
	public ArrayList<Carta> buscarCartas(String nomeBusca) {
	    ArrayList<Carta> lista = new ArrayList<>();
	    String sql = "SELECT * FROM cartas WHERE nome LIKE ?";
	
	    try (Connection conn = Conexao.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	
	        stmt.setString(1, "%" + nomeBusca + "%");
	
	        ResultSet rs = stmt.executeQuery();
	
	        while (rs.next()) {
	            String nome = rs.getString("nome");
	            String tipo = rs.getString("tipo");
	
	            if (tipo.equalsIgnoreCase("Unidade")) {
	                int atk = rs.getInt("atk");
	                int def = rs.getInt("def");
	                lista.add(new Unidade(nome, atk, def));
	
	            } else if (tipo.equalsIgnoreCase("Armamento")) {
	                int bonus = rs.getInt("bonus");
	                lista.add(new Armamento(nome, bonus));
	            }
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	    return lista;
	}
}