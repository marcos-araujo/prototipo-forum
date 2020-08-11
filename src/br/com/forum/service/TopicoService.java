package br.com.forum.service;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.forum.dao.TopicoDAO;
import br.com.forum.model.Pagina;
import br.com.forum.model.Topico;

public class TopicoService {
	
	private TopicoDAO topicoDAO;
	
	public static final Integer TAMANHO_PAGINA = 10;
	
	public TopicoService(TopicoDAO topicoDAO) {
		this.topicoDAO = topicoDAO;
	}

	public Pagina recuperaPaginaRespostas(Long idTopico, Long pagina) throws SQLException {
		Topico topico = topicoDAO.busca(idTopico);
		
		ArrayList<Topico> respostas = new ArrayList<Topico>();
		respostas.add(topico);

		for (int indicePilha = 0; indicePilha < respostas.size(); indicePilha++) {
			ArrayList<Topico> listaRespostas = topicoDAO.listaRespostas(respostas.get(indicePilha).getId());
			respostas.addAll(indicePilha+1, listaRespostas);
		}

		Long numeroPaginas = recuperaNumeroPaginas(Long.valueOf(respostas.size()));
		ArrayList<Topico> paginaRespostas = extrairPagina(respostas, pagina);
		
		return montaPagina(paginaRespostas, numeroPaginas);
	}
	
	public ArrayList<Topico> extrairPagina(ArrayList<Topico> respostas, Long pagina){
		Integer inicio = (pagina.intValue()-1) * TAMANHO_PAGINA;
		Integer fim = inicio + TAMANHO_PAGINA > respostas.size() ? respostas.size() : inicio + TAMANHO_PAGINA;
		
		return new ArrayList<Topico>(respostas.subList(inicio, fim));
	}
	
	public Pagina recuperaPaginaPerguntas(Integer numeroPagina) throws SQLException {
		Integer inicio = (numeroPagina-1) * TAMANHO_PAGINA;
		
		ArrayList<Topico> paginaPerguntas = topicoDAO.recuperaTopicos(inicio);
		Long numeroPaginas = recuperaNumeroPaginas(topicoDAO.numeroTopicos());
		
		return montaPagina(paginaPerguntas, numeroPaginas);
	}
	
	public Pagina montaPagina(ArrayList<Topico> topicos, Long numeroPaginas) {
		Pagina paginaObjeto = new Pagina();
		paginaObjeto.setLista(topicos);
		paginaObjeto.setPaginacao(numeroPaginas);
		return paginaObjeto;
	}
	
	public Long recuperaNumeroPaginas(Long numeroRespostas) {
		return Long.valueOf(numeroRespostas % TAMANHO_PAGINA == 0 ? numeroRespostas / TAMANHO_PAGINA : numeroRespostas/TAMANHO_PAGINA + 1);		
	}
	
}
