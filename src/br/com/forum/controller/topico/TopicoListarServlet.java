package br.com.forum.controller.topico;

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

@WebServlet("/topicoListar")
public class TopicoListarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Connection connection = (Connection) request.getAttribute("connection");
		
		TopicoDAO dao = new TopicoDAO(connection);
		
		ArrayList<Topico> lista = null;
		Long topico = 1l;
		try {
			topico = Long.valueOf(request.getParameter("t") != null ? request.getParameter("t"): "1");
			Long pagina = Long.valueOf(request.getParameter("p") != null ? request.getParameter("p"): "1");
			
			lista = dao.listaTopicos(topico, pagina);
		
		} catch (NumberFormatException | SQLException e) {
		}
		request.setAttribute("lista", lista);
		
		Long numeroDeTopicos = null;
		try {
			numeroDeTopicos = dao.quantidadeTopicos(topico);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Long paginacao = numeroDeTopicos % 10 == 0 ? numeroDeTopicos / 10 : numeroDeTopicos/10+1;
		
		request.setAttribute("paginacao", paginacao);
		
		request.getRequestDispatcher("WEB-INF/views/topico/topico.jsp").forward(request, response);

	}

}