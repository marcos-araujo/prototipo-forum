package br.com.forum.controller.topico;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.blacklist.BlackList;
import br.com.forum.dao.TopicoDAO;
import br.com.forum.model.Topico;

@WebServlet("/topicoAdicionar")
public class TopicoAdicionarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Connection connection = (Connection) request.getAttribute("connection");
		
		BlackList blackList = new BlackList();
		
		String texto = request.getParameter("texto");
		String idPai = request.getParameter("idPai");
		
		String textoVerificado = null;
		
		try {
			textoVerificado = blackList.verificar(texto, connection);
		} catch (SQLException e1) {
		}
		
		Topico topico = new Topico();
		topico.setTexto(textoVerificado);
		topico.setIdPai(new Long(idPai));
		
		TopicoDAO dao = new TopicoDAO(connection);
		
		try {
			dao.adiciona(topico);
		} catch (SQLException e) {
		}
		
		request.getRequestDispatcher("/forum").forward(request, response);
			
	}

}