package br.com.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.forum.lista.negada.ListaNegada;
import br.com.forum.model.Topico;

public class TopicoDAO{
	
	private Connection connection;
	private ArrayList<Topico> topicos; 
	private PreparedStatement stmtAdiciona;
	private PreparedStatement stmtBusca;
	private PreparedStatement stmtLista;
	private PreparedStatement stmtListaTopicos;
	private ListaNegada listaNegada = new ListaNegada();
	
	public TopicoDAO(Connection connection){
		this.connection = connection;	
		topicos = new ArrayList<Topico>();
	}
	
	public void adiciona(Topico topico){
		try{
			stmtAdiciona = this.connection.prepareStatement("INSERT INTO TOPICO(TEXTO, ID_PAI, DATA) VALUES(?, ?, ?)");
			stmtAdiciona.setString(1, listaNegada.verificaListaNegada(topico.getTexto(), this.connection));
			stmtAdiciona.setLong(2, topico.getIdPai());
			stmtAdiciona.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			stmtAdiciona.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				stmtAdiciona.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public Topico busca(Long id){
		Topico topico = new Topico();
		ResultSet rs = null;
		try{
			stmtBusca = this.connection.prepareStatement("SELECT ID, TEXTO, ID_PAI, DATA FROM TOPICO WHERE ID = ?");
			stmtBusca.setLong(1, id);
			rs = stmtBusca.executeQuery();
			if(rs.next()){
				topico.setId(rs.getLong("id"));
				topico.setTexto(rs.getString("texto"));
				topico.setIdPai(rs.getLong("id_pai"));
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getTimestamp("data").getTime());
				topico.setData(data);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				stmtBusca.close();
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return topico;
	}
	
	public ArrayList<Topico> lista(){
		ResultSet rs = null;
		try{
			stmtLista = this.connection.prepareStatement("SELECT ID, TEXTO, ID_PAI, DATA FROM TOPICO WHERE ID_PAI = 0");
			rs = stmtLista.executeQuery();
			topicos.clear();
			while(rs.next()){
				Topico topico = new Topico();
				topico.setId(rs.getLong("id"));
				topico.setTexto(rs.getString("texto"));
				topico.setIdPai(rs.getLong("id_pai"));
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getTimestamp("data").getTime());
				topico.setData(data);
				topicos.add(topico);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				stmtLista.close();
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return topicos;
	}
	
	public ArrayList<Topico>listaTopicos(long id, long exibicao){
		ResultSet rs = null;
		try{
			stmtListaTopicos = this.connection.prepareStatement("SELECT ID, TEXTO, ID_PAI, DATA FROM TOPICO WHERE ID_PAI = ?");
			topicos = new ArrayList<Topico>();
			Topico topico = this.busca(id);
			topico.setNivel(new Long(exibicao).toString());
			topicos.add(topico);
			stmtListaTopicos.setLong(1, id);
			rs = stmtListaTopicos.executeQuery();
			id = 1;
			while(rs.next()){
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
			id = 1;
			int index = 2;
			if(topicos.size()>1)
				for(int indicePilha=1; indicePilha<topicos.size(); indicePilha++){
					stmtListaTopicos.setLong(1, topicos.get(indicePilha).getId());
					rs = stmtListaTopicos.executeQuery();
					index = indicePilha + 1;
					while(rs.next()){
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
					id =1 ;
				}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				stmtListaTopicos.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return topicos;
	}

}