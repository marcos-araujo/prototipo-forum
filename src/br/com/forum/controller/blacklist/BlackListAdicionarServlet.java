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

@WebServlet("/blacklistAdicionar")
public class BlackListAdicionarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Connection connection = (Connection) request.getAttribute("connection");

		String texto = request.getParameter("palavra");
		ItemBlackList palavra = new ItemBlackList();
		palavra.setPalavra(texto);
		
		BlackListDAO dao = new BlackListDAO(connection);
		
		try {
			dao.adiciona(palavra);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/blacklistListar").forward(request, response);

	}

}