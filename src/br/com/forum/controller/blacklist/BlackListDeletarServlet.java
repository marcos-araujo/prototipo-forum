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

@WebServlet("/blacklistDeletar")
public class BlackListDeletarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Connection connection = (Connection) request.getAttribute("connection");
		
		String id = request.getParameter("idPalavra");
		BlackListDAO palavra = new BlackListDAO(connection);
		
		try {
			palavra.delete(Long.valueOf(id));
		} catch (NumberFormatException | SQLException e) {
		}
		
		request.getRequestDispatcher("/blacklistListar").forward(request, response);

	}

}