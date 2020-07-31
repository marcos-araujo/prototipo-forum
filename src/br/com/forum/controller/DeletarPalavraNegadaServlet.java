package br.com.forum.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.forum.dao.PalavraNegadaDAO;

@WebServlet("/deletarPalavraNegada")
public class DeletarPalavraNegadaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Connection connection = (Connection) request.getAttribute("connection");

		String id = request.getParameter("idPalavra");
		PalavraNegadaDAO palavra = new PalavraNegadaDAO(connection);
		
		try {
			palavra.delete(Long.valueOf(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/listarPalavraNegada").forward(request, response);

	}

}