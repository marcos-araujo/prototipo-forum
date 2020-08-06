package br.com.forum.controller.blacklist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.dao.BlackListDAO;
import br.com.forum.model.ItemBlackList;

@WebServlet("/blacklistListar")
public class BlackListListarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Connection connection = (Connection) request.getAttribute("connection");

		BlackListDAO dao = new BlackListDAO(connection);
		
		List<ItemBlackList> lista = null;
		
		try {
			lista = dao.lista();
		} catch (SQLException e) {
		}

		request.setAttribute("lista", lista);
		
		request.getRequestDispatcher("WEB-INF/views/blacklist/blackList.jsp").forward(request, response);

	}

}