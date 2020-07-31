package br.com.forum.lista.negada;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.forum.dao.PalavraNegadaDAO;
import br.com.forum.model.PalavraNegada;

public class ListaNegada{

	private PalavraNegadaDAO palavraDAO;
	private ArrayList<PalavraNegada> listaPalavra;

	//Lógica de validação dos comentários contra a black list 
	public String verificaListaNegada(String texto, Connection connection) throws SQLException{
		
		palavraDAO = new PalavraNegadaDAO(connection);
		
		listaPalavra = palavraDAO.lista();
		
		for(int x=0; x<listaPalavra.size(); x++){
			//Verificar Possibilidades
			String regex = "(?i)" + listaPalavra.get(x).getPalavra().replace("a", "(a|@|ã|á|à|â)").replace("i", "(i|1|í|ì)").replace("o", "(o|0|ó|ò|ô|õ)").replace("s", "(s|&)");
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(texto);
            if(m.find())
            	texto = texto.replaceAll(m.group(), m.group().replaceAll(".", "x"));
		}
		return texto;
	}

}