package br.com.forum.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.dao.PalavraNegadaDAO;
import br.com.forum.dao.TopicoDAO;
import br.com.forum.model.PalavraNegada;
import br.com.forum.model.Topico;

@WebServlet("/forum")
public class ServletController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Connection connection = (Connection) request.getAttribute("connection");
		
		if (request.getParameter("idTopico") != null) { // Deletar Palavra
			String id = request.getParameter("idTopico");
			PalavraNegadaDAO palavra = new PalavraNegadaDAO(connection);
			palavra.delete(new Long(id));
			request.getRequestDispatcher("/listaNegada.jsp").forward(request, response);
			
		} else if (request.getParameter("listaNegada") != null) { // Inserir Palavra
			String texto = request.getParameter("palavra");
			PalavraNegadaDAO dao = new PalavraNegadaDAO(connection);
			PalavraNegada palavra = new PalavraNegada();
			palavra.setPalavra(texto);
			dao.adiciona(palavra);
			request.getRequestDispatcher("/listaNegada.jsp").forward(request, response);
		
		} else {
			TopicoDAO dao = new TopicoDAO(connection);
			ArrayList<Topico> lista = dao.lista();
			request.setAttribute("lista", lista);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

}