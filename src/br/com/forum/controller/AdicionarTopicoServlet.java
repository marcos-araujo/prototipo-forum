package br.com.forum.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.dao.TopicoDAO;
import br.com.forum.lista.negada.ListaNegada;
import br.com.forum.model.Topico;

@WebServlet("/adicionarTopico")
public class AdicionarTopicoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Connection connection = (Connection) request.getAttribute("connection");
		
		ListaNegada blackList = new ListaNegada();
		
		String texto = request.getParameter("texto");
		String idPai = request.getParameter("idPai");
		String pagina = (request.getParameter("topico") != null) ? "topico.jsp?t=" + new String(request.getParameter("topico"))	: "";
		
		if (pagina.equals(""))
			pagina = "?";
		
		pagina = pagina + new String((request.getParameter("pagina") == "") ? "&p=1" + new String(request.getParameter("pagina")) : "");
		pagina = pagina + new String((request.getParameter("n") != null) ? "&n=" + new String(request.getParameter("n")) : "");
		pagina = pagina + new String((request.getParameter("numeroExibicao") != null) ? "&n=" + new String(request.getParameter("numeroExibicao")) : "");
		
		String textoVerificado = null;
		
		try {
			textoVerificado = blackList.verificaListaNegada(texto, connection);
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
		
		request.getRequestDispatcher("/forum" + pagina).forward(request, response);
			
	}

}