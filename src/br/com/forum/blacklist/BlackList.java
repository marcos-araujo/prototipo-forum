package br.com.forum.blacklist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.forum.dao.BlackListDAO;
import br.com.forum.model.ItemBlackList;

public class BlackList {

	public String verificar(String texto, Connection connection) throws SQLException {

		BlackListDAO palavraDAO = new BlackListDAO(connection);

		ArrayList<ItemBlackList> listaPalavra = palavraDAO.lista();

		for (ItemBlackList palavraNegada : listaPalavra) {

			String regex = "(?i)" + palavraNegada.getPalavra().replace("a", "(a|@|�|�|�|�)")
					.replace("i", "(i|1|�|�)").replace("o", "(o|0|�|�|�|�)").replace("s", "(s|&)");
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(texto);
			if (m.find())
				texto = texto.replaceAll(m.group(), m.group().replaceAll(".", "x"));
		}
		return texto;

	}

}