package br.com.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.forum.model.PalavraNegada;

public class PalavraNegadaDAO {

	private Connection connection;

	private ArrayList<PalavraNegada> palavras;
	private PreparedStatement stmtLista;
	private PreparedStatement stmtAdiciona;
	private PreparedStatement stmtDelete;

	public PalavraNegadaDAO(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<PalavraNegada> lista() {
		ResultSet rs = null;
		try {
			palavras = new ArrayList<PalavraNegada>();

			stmtLista = this.connection.prepareStatement("SELECT ID, PALAVRA FROM PALAVRA_NEGADA");
			rs = stmtLista.executeQuery();

			while (rs.next()) {
				PalavraNegada palavra = new PalavraNegada();
				palavra.setId(rs.getLong("id"));
				palavra.setPalavra(rs.getString("palavra"));
				palavras.add(palavra);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmtLista.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return palavras;
	}

	public void adiciona(PalavraNegada palavra) {
		try {
			stmtAdiciona = this.connection.prepareStatement("INSERT INTO PALAVRA_NEGADA(PALAVRA) VALUES(?)");
			stmtAdiciona.setString(1, palavra.getPalavra());
			stmtAdiciona.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmtAdiciona.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(long id) {
		try {
			stmtDelete = this.connection.prepareStatement("DELETE FROM PALAVRA_NEGADA WHERE ID = ?");
			stmtDelete.setLong(1, id);
			stmtDelete.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmtDelete.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}