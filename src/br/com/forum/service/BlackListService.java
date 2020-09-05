package br.com.forum.service;

import java.sql.SQLException;
import java.util.List;

import br.com.forum.dao.BlackListDAO;
import br.com.forum.model.ItemBlackList;

public class BlackListService {
	
	private BlackListDAO blackListDAO;
	
	public BlackListService(BlackListDAO blackListDAO) {
		this.blackListDAO = blackListDAO;
	}

	public List<ItemBlackList> recuperaBlackList() throws SQLException {
		return recuperaBlackList(blackListDAO.lista());
	}
	
	public void deletarItem(Long id) throws SQLException {
		blackListDAO.delete(id);
	}

	public void adicionaItem(ItemBlackList palavra) throws SQLException {
		blackListDAO.adiciona(palavra);
	}
	
	private List<ItemBlackList> recuperaBlackList(List<ItemBlackList> lista){

		lista.forEach(item -> {
			item.setPalavra(item.getPalavra().replace("(?i)", "").replace("(a|@|ã|á|à|â)", "a")
					.replace("(i|1|í|ì)", "i").replace("(o|0|ó|ò|ô|õ)", "o").replace("(s|&)", "s"));
		});
		
		return lista;
	}
}