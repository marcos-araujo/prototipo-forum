package br.com.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.forum.dao.sql.Queries;
import br.com.forum.dao.util.RsTopico;
import br.com.forum.model.Topico;

public class TopicoDAO {

	private Connection connection;

	public TopicoDAO(Connection connection) {
		this.connection = connection;
	}

	public Topico busca(Long id) throws SQLException {
		
		try (PreparedStatement stmtBusca = this.connection.prepareStatement(Queries.SELECT)) {
			
			stmtBusca.setLong(1, id);
			ResultSet rs = stmtBusca.executeQuery();
			
			Topico topico = new Topico();

			if (rs.next())
				topico = RsTopico.getTopico(rs);
			
			return topico;
		}
		
	}

	public void adiciona(Topico topico) throws SQLException {

		try (PreparedStatement stmtAdiciona = this.connection.prepareStatement(Queries.INSERT)) {
			stmtAdiciona.setString(1, topico.getTexto());
			stmtAdiciona.setLong(2, topico.getIdPai());
			stmtAdiciona.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			stmtAdiciona.setLong(4, topico.getNivel());
			stmtAdiciona.execute();
		}

	}

	public ArrayList<Topico> listaTopico() throws SQLException {

		try (PreparedStatement stmt = this.connection.prepareStatement(Queries.SELECT_PERGUNTAS); ResultSet rs = stmt.executeQuery()) {

			ArrayList<Topico> topicos = new ArrayList<Topico>();
			while (rs.next()) 
				topicos.add(RsTopico.getTopico(rs));

			return topicos;
			
		}
	}

	public ArrayList<Topico> recuperaTopicos(Integer inicio) throws SQLException {
		ArrayList<Topico> topicos = new ArrayList<Topico>();
		
		try (PreparedStatement stmt = this.connection.prepareStatement(Queries.SELECT_PAGINA_PERGUNTAS)) {
			
			stmt.setInt(1, inicio);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				topicos.add(RsTopico.getTopico(rs));
			
			return topicos;
			
		}
	}
	
	public Long numeroTopicos() throws SQLException {
		try (PreparedStatement stmt = this.connection.prepareStatement(Queries.SELECT_COUNT)) {
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Long quantidade = rs.getLong("QUANTIDADE");
			return quantidade;
		}
	}
	
	public ArrayList<Topico> listaRespostas(Long idPergunta) throws SQLException {
		ArrayList<Topico> topicos = new ArrayList<Topico>();
		
		try (PreparedStatement stmt = this.connection.prepareStatement(Queries.SELECT_RESPOSTAS)) {
			
			stmt.setLong(1, idPergunta);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				topicos.add(RsTopico.getTopico(rs));
			
			return topicos;
			
		}
	}
	
}