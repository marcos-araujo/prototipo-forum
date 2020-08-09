package br.com.forum.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Topico{
	
	private Long id;
	private String texto;
	private Long idPai;
	private Calendar data;
	private Long nivel;
	
	
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	
	public String getTexto(){
		return texto;
	}
	public void setTexto(String texto){
		this.texto = texto;
	}
	
	public Long getIdPai(){
		return idPai;
	}
	public void setIdPai(Long idPai){
		this.idPai = idPai;
	}
	
	public Calendar getData(){
		return data;
	}
	public void setData(Calendar data){
		this.data = data;
	}
	
	public Long getNivel(){
		return nivel;
	}
	public void setNivel(Long nivel){
		this.nivel = nivel;
	}
	
	public String getDataFormatada(){
		DateFormat dt = new SimpleDateFormat("d 'de' MMMM 'de' yyyy");
		return dt.format(data.getTime());
	}
	
}