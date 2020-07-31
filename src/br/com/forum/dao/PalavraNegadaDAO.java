package br.com.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.forum.model.PalavraNegada;

public class PalavraNegadaDAO {

	private Connection connection;

	public PalavraNegadaDAO(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<PalavraNegada> lista() throws SQLException {

		try (PreparedStatement stmtLista = this.connection.prepareStatement("SELECT ID, PALAVRA FROM PALAVRA_NEGADA"); ResultSet rs = stmtLista.executeQuery()) {

			ArrayList<PalavraNegada> palavras = new ArrayList<PalavraNegada>();

			while (rs.next()) {
				PalavraNegada palavra = new PalavraNegada();
				palavra.setId(rs.getLong("id"));
				palavra.setPalavra(rs.getString("palavra"));
				palavras.add(palavra);
			}

			return palavras;
		}
	}

	public void adiciona(PalavraNegada palavra) throws SQLException {
		
		try (PreparedStatement stmtAdiciona = this.connection.prepareStatement("INSERT INTO PALAVRA_NEGADA(PALAVRA) VALUES(?)")){
			stmtAdiciona.setString(1, palavra.getPalavra());
			stmtAdiciona.execute();
		} 

	}

	public void delete(long id) throws SQLException {
		
		try (PreparedStatement stmtDelete = connection.prepareStatement("DELETE FROM PALAVRA_NEGADA WHERE ID = ?")){
			stmtDelete.setLong(1, id);
			stmtDelete.execute();
		}
		
	}

}