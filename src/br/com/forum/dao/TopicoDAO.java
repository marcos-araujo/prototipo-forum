package br.com.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.forum.model.Topico;

public class TopicoDAO {

	private Connection connection;

	public TopicoDAO(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(Topico topico) throws SQLException {

		try (PreparedStatement stmtAdiciona = this.connection.prepareStatement("INSERT INTO TOPICO(TEXTO, ID_PAI, DATA) VALUES(?, ?, ?)")) {
			stmtAdiciona.setString(1, topico.getTexto());
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

		try (PreparedStatement stmt = this.connection.prepareStatement("SELECT ID, TEXTO, ID_PAI, DATA FROM TOPICO WHERE ID_PAI = 0"); ResultSet rs = stmt.executeQuery()) {

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

	public ArrayList<Topico> recuperarPagina(Integer pagina) throws SQLException {
		ResultSet rs = null;
		
		try (PreparedStatement stmt = this.connection.prepareStatement("SELECT ID, TEXTO, ID_PAI, DATA FROM TOPICO WHERE ID_PAI = 0 ORDER BY ID DESC LIMIT 10 OFFSET ?")) {
			stmt.setInt(1, (pagina-1)*10);
			rs = stmt.executeQuery();
					
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

	public Long numeroDeTopicos() throws SQLException {
		ResultSet rs = null;
		try (PreparedStatement stmt = this.connection.prepareStatement("SELECT COUNT(*) AS QUANTIDADE FROM TOPICO WHERE ID_PAI = 0 ")) {
			rs = stmt.executeQuery();
			rs.next();
			Long quantidade = rs.getLong("QUANTIDADE");
			return quantidade;
		}
		
	}
	
	public Long quantidadeTopicos(Long idTopico) throws SQLException {

		Long quantidade = 0l;
		
		try (PreparedStatement stmt = this.connection.prepareStatement("SELECT ID, TEXTO, ID_PAI, DATA FROM TOPICO WHERE ID_PAI = ?")) {

			ArrayList<Topico> topicos = new ArrayList<Topico>();

			Topico topico = this.busca(idTopico);
			topico.setNivel(idTopico.toString());
			topicos.add(topico);
			quantidade++;

			stmt.setLong(1, idTopico);
			try(ResultSet rs = stmt.executeQuery()){

				idTopico = 1l;
				while (rs.next()) {
					topico = new Topico();
					topico.setId(rs.getLong("id"));
					topico.setTexto(rs.getString("texto"));
					topico.setIdPai(rs.getLong("id_pai"));
					topico.setNivel("<spam style='padding: 0 20px;'></spam>" + idTopico + "." + idTopico);
					Calendar data = Calendar.getInstance();
					data.setTimeInMillis(rs.getTimestamp("data").getTime());
					topico.setData(data);
					topicos.add(topico);
					quantidade++;
					idTopico++;
				}
				
			}

			idTopico = 1l;
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
							topico.setNivel("<spam style='padding: 0 20px;'></spam>" + topicos.get(indicePilha).getNivel() + "." + idTopico);
							topicos.add(index, topico);
							quantidade++;
							index++;
							idTopico++;
						}
						idTopico = 1l;
						
					}

				}
			}
			
			return quantidade;
		}
	}

	public ArrayList<Topico> listaTopicos(Long idTopico, Long pagina) throws SQLException {

		try (PreparedStatement stmt = this.connection.prepareStatement("SELECT ID, TEXTO, ID_PAI, DATA FROM TOPICO WHERE ID_PAI = ?")) {

			ArrayList<Topico> topicos = new ArrayList<Topico>();

			Topico topico = this.busca(idTopico);
			topico.setNivel(idTopico.toString());
			topicos.add(topico);

			stmt.setLong(1, idTopico);
			try(ResultSet rs = stmt.executeQuery()){

				idTopico = 1l;
				while (rs.next()) {
					topico = new Topico();
					topico.setId(rs.getLong("id"));
					topico.setTexto(rs.getString("texto"));
					topico.setIdPai(rs.getLong("id_pai"));
					topico.setNivel("<spam style='padding: 0 20px;'></spam>" + idTopico + "." + idTopico);
					Calendar data = Calendar.getInstance();
					data.setTimeInMillis(rs.getTimestamp("data").getTime());
					topico.setData(data);
					topicos.add(topico);
					idTopico++;
				}
				
			}

			idTopico = 1l;
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
							topico.setNivel("<spam style='padding: 0 20px;'></spam>" + topicos.get(indicePilha).getNivel() + "." + idTopico);
							topicos.add(index, topico);
							index++;
							idTopico++;
						}
						idTopico = 1l;
						
					}

				}
			}
			
			Integer inicio = (pagina.intValue()-1)*10;
			Integer fim = inicio + 10 > topicos.size() ? topicos.size() : inicio + 10;
			
			return new ArrayList<Topico>(topicos.subList(inicio, fim));
		}
	}

}