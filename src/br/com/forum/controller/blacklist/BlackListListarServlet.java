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
import br.com.forum.service.BlackListService;

@WebServlet("/blacklistListar")
public class BlackListListarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			Connection connection = (Connection) request.getAttribute("connection");
			
			BlackListDAO blackListDAO = new BlackListDAO(connection);
			BlackListService blackListService = new BlackListService(blackListDAO);

			List<ItemBlackList> lista = blackListService.recuperaBlackList();

			request.setAttribute("lista", lista);
			request.getRequestDispatcher("WEB-INF/views/blacklist/blackList.jsp").forward(request, response);
		} catch (SQLException e) {
		}

	}

}