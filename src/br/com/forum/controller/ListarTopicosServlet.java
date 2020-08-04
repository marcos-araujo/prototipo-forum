package br.com.forum.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.dao.TopicoDAO;
import br.com.forum.model.Topico;

@WebServlet("/listarTopicos")
public class ListarTopicosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Connection connection = (Connection) request.getAttribute("connection");
		
		TopicoDAO dao = new TopicoDAO(connection);
		
		ArrayList<Topico> lista = null;
		try {
			lista = dao.listaTopicos(Long.valueOf(request.getParameter("t")));
		} catch (NumberFormatException | SQLException e) {
		}
		
		request.setAttribute("lista", lista);
		
		request.getRequestDispatcher("WEB-INF/views/topico/topico.jsp").forward(request, response);

	}

}