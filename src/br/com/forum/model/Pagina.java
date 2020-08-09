package br.com.forum.model;

import java.util.ArrayList;

public class Pagina{
	
	private ArrayList<Topico> lista;
	private Long paginacao;
	
	public ArrayList<Topico> getLista() {
		return lista;
	}
	public void setLista(ArrayList<Topico> lista) {
		this.lista = lista;
	}
	
	public Long getPaginacao() {
		return paginacao;
	}
	public void setPaginacao(Long paginacao) {
		this.paginacao = paginacao;
	}

}