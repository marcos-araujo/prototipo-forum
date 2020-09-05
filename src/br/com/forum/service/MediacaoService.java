package br.com.forum.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.forum.dao.BlackListDAO;
import br.com.forum.model.ItemBlackList;

public class MediacaoService {

	public String verificar(String texto, BlackListDAO blackListDAO) throws SQLException {
		 
		AtomicReference<String> textR = new AtomicReference<String>(texto);
		 
		ArrayList<ItemBlackList> listaPalavra = blackListDAO.lista();
		
		listaPalavra.stream().forEach(palavra -> {
			Pattern p = Pattern.compile(palavra.getPalavra());
			Matcher m = p.matcher(texto);
			
			if (m.find()) 
				textR.set(texto.replaceAll(m.group(), m.group().replaceAll(".", "x")));
		});

		return textR.get();

	}

}