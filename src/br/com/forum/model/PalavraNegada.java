package br.com.forum.model;

public class PalavraNegada{
	
	private Long id;
	private String palavra;
	
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	
	public String getPalavra(){
		return palavra;
	}
	public void setPalavra(String palavra){
		this.palavra = palavra;
	}

}