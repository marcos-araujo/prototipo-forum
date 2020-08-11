package br.com.forum.controller.topico;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.dao.BlackListDAO;
import br.com.forum.dao.TopicoDAO;
import br.com.forum.model.Topico;
import br.com.forum.service.MediacaoService;

@WebServlet("/topicoAdicionar")
public class TopicoAdicionarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			Connection connection = (Connection) request.getAttribute("connection");
			MediacaoService mediacao = new MediacaoService();
			BlackListDAO blackListaDAO = new BlackListDAO(connection);
	
			String idTopico = request.getParameter("idTopico");
			String idPai = request.getParameter("idPai");
			String texto = mediacao.verificar(request.getParameter("texto"), blackListaDAO);
			String nivel = request.getParameter("nivel");
			
			Topico topico = new Topico();
			topico.setTexto(texto);
			topico.setIdPai(Long.valueOf(idPai));
			topico.setNivel(Long.valueOf(nivel));
	
			TopicoDAO topicoDAO = new TopicoDAO(connection);
			topicoDAO.adiciona(topico);
			
			boolean ehPergunta = idTopico == null || idTopico.equals("null");
			
			if(ehPergunta)
				response.sendRedirect("forum");
			else
				response.sendRedirect("topicoListar?t=" + idTopico);

		} catch (SQLException e) {
		}
			
	}

}