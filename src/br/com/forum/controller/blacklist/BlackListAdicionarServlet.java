package br.com.forum.controller.blacklist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.dao.BlackListDAO;
import br.com.forum.model.ItemBlackList;
import br.com.forum.service.BlackListService;

@WebServlet("/blacklistAdicionar")
public class BlackListAdicionarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			Connection connection = (Connection) request.getAttribute("connection");
			
			BlackListDAO blackListDAO = new BlackListDAO(connection);
			BlackListService blackListService = new BlackListService(blackListDAO);

			String texto = request.getParameter("palavra");
			
			texto = "(?i)" + texto.replace("a", "(a|@|ã|á|à|â)").replace("i", "(i|1|í|ì)").replace("o", "(o|0|ó|ò|ô|õ)").replace("s", "(s|&)");
			
			ItemBlackList palavra = new ItemBlackList();
			palavra.setPalavra(texto);
			
			blackListService.adicionaItem(palavra);
		
			response.sendRedirect("blacklistListar");
			
		} catch (SQLException e) {
		}
	}

}