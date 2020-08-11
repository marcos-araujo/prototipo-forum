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
		return blackListDAO.lista();
	}
	
	public void deletarItem(Long id) throws SQLException {
		blackListDAO.delete(id);
	}

	public void adicionaItem(ItemBlackList palavra) throws SQLException {
		blackListDAO.adiciona(palavra);
	}
	
}