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

		String idTopico = request.getParameter("idTopico");
		
		String idPai = request.getParameter("idPai");
		String texto = request.getParameter("texto");
		String nivel = request.getParameter("nivel");
		
		String textoVerificado = null;
		
		try {
			textoVerificado = blackList.verificar(texto, connection);
		} catch (SQLException e) {
		}

		Topico topico = new Topico();
		topico.setTexto(textoVerificado);
		topico.setIdPai(Long.valueOf(idPai));
		topico.setNivel(Long.valueOf(nivel));

		try {
			TopicoDAO dao = new TopicoDAO(connection);
			dao.adiciona(topico);
		} catch (SQLException e) {
		}
		
		boolean ehTopico = idTopico == null || idTopico.equals("null");
		
		if(ehTopico)
			response.sendRedirect("forum");
		else
			response.sendRedirect("topicoListar?t=" + idTopico);
			
	}

}