package br.com.forum.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.dao.PalavraNegadaDAO;
import br.com.forum.model.PalavraNegada;

@WebServlet("/adicionarPalavraNegada")
public class AdicionarPalavraNegadaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Connection connection = (Connection) request.getAttribute("connection");

		String texto = request.getParameter("palavra");
		PalavraNegada palavra = new PalavraNegada();
		palavra.setPalavra(texto);
		
		PalavraNegadaDAO dao = new PalavraNegadaDAO(connection);
		dao.adiciona(palavra);
		
		request.getRequestDispatcher("/listarPalavraNegada").forward(request, response);

	}

}