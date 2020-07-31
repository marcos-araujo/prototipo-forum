package br.com.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.forum.lista.negada.ListaNegada;
import br.com.forum.model.Topico;

public class TopicoDAO {

	private Connection connection;
	private ListaNegada listaNegada = new ListaNegada();

	public TopicoDAO(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(Topico topico) throws SQLException {

		try (PreparedStatement stmtAdiciona = this.connection.prepareStatement("INSERT INTO TOPICO(TEXTO, ID_PAI, DATA) VALUES(?, ?, ?)")) {
			stmtAdiciona.setString(1, listaNegada.verificaListaNegada(topico.getTexto(), this.connection));
			stmtAdiciona.setLong(2, topico.getIdPai());
			stmtAdiciona.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			stmtAdiciona.execute();
		}

	}

	public Topico busca(Long id) throws SQLException {

		Topico topico = new Topico();
		ResultSet rs = null;

		try (PreparedStatement stmtBusca = this.connection.prepareStatement("SELECT ID, TEXTO, ID_PAI, DATA FROM TOPICO WHERE ID = ?")) {

			stmtBusca.setLong(1, id);
			rs = stmtBusca.executeQuery();
			
			if (rs.next()) {
				topico.setId(rs.getLong("id"));
				topico.setTexto(rs.getString("texto"));
				topico.setIdPai(rs.getLong("id_pai"));
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getTimestamp("data").getTime());
				topico.setData(data);
			}

		}

		return topico;
	}

	public ArrayList<Topico> lista() throws SQLException {

		try (PreparedStatement stmt = this.connection.prepareStatement("SELECT ID, TEXTO, ID_PAI, DATA FROM TOPICO WHERE ID_PAI = 0");	ResultSet rs = stmt.executeQuery()) {

			ArrayList<Topico> topicos = new ArrayList<Topico>();

			while (rs.next()) {
				Topico topico = new Topico();
				topico.setId(rs.getLong("id"));
				topico.setTexto(rs.getString("texto"));
				topico.setIdPai(rs.getLong("id_pai"));
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getTimestamp("data").getTime());
				topico.setData(data);
				topicos.add(topico);
			}
			return topicos;
			
		}
	}

	public ArrayList<Topico> listaTopicos(long id, long exibicao) throws SQLException {

		try (PreparedStatement stmt = this.connection.prepareStatement("SELECT ID, TEXTO, ID_PAI, DATA FROM TOPICO WHERE ID_PAI = ?")) {

			ArrayList<Topico> topicos = new ArrayList<Topico>();

			Topico topico = this.busca(id);
			topico.setNivel(new Long(exibicao).toString());
			topicos.add(topico);

			stmt.setLong(1, id);
			try(ResultSet rs = stmt.executeQuery()){

				id = 1;
				while (rs.next()) {
					topico = new Topico();
					topico.setId(rs.getLong("id"));
					topico.setTexto(rs.getString("texto"));
					topico.setIdPai(rs.getLong("id_pai"));
					topico.setNivel("<spam style='padding: 0 20px;'></spam>" + exibicao + "." + id);
					Calendar data = Calendar.getInstance();
					data.setTimeInMillis(rs.getTimestamp("data").getTime());
					topico.setData(data);
					topicos.add(topico);
					id++;
				}
				
			}

			id = 1;
			int index = 2;

			if (topicos.size() > 1) {

				for (int indicePilha = 1; indicePilha < topicos.size(); indicePilha++) {

					stmt.setLong(1, topicos.get(indicePilha).getId());
					try(ResultSet rs = stmt.executeQuery()){
						
						index = indicePilha + 1;
						while (rs.next()) {
							
							topico = new Topico();
							topico.setId(rs.getLong("id"));
							topico.setTexto(rs.getString("texto"));
							topico.setIdPai(rs.getLong("id_pai"));
							Calendar data = Calendar.getInstance();
							data.setTimeInMillis(rs.getTimestamp("data").getTime());
							topico.setData(data);
							topico.setNivel("<spam style='padding: 0 20px;'></spam>" + topicos.get(indicePilha).getNivel() + "." + id);
							topicos.add(index, topico);
							index++;
							id++;
						}
						id = 1;
						
					}

				}
			}

			return topicos;
		}
	}

}