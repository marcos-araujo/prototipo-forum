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
import br.com.forum.service.BlackListService;

@WebServlet("/blacklistDeletar")
public class BlackListDeletarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			Connection connection = (Connection) request.getAttribute("connection");
			
			String id = request.getParameter("idPalavra");

			BlackListDAO blackListDAO = new BlackListDAO(connection);
			BlackListService blackListService = new BlackListService(blackListDAO);
			
			blackListService.deletarItem(Long.valueOf(id));
			
			response.sendRedirect("blacklistListar");
			
		} catch (NumberFormatException | SQLException e) {
		}
	}

}