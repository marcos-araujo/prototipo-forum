package br.com.forum.dao.sql;

import br.com.forum.service.TopicoService;

public class Queries {
	
	public static final String SELECT = "SELECT ID, TEXTO, ID_PAI, DATA, NIVEL FROM TOPICO WHERE ID = ?";
	
	public static final String SELECT_PERGUNTAS = "SELECT ID, TEXTO, ID_PAI, DATA, NIVEL FROM TOPICO WHERE ID_PAI = 0";

	public static final String SELECT_RESPOSTAS = "SELECT ID, TEXTO, ID_PAI, DATA, NIVEL FROM TOPICO WHERE ID_PAI = ?";

	public static final String SELECT_COUNT = "SELECT COUNT(*) AS QUANTIDADE FROM TOPICO WHERE ID_PAI = 0";
	
	public static final String SELECT_PAGINA_PERGUNTAS = "SELECT ID, TEXTO, ID_PAI, DATA, NIVEL FROM TOPICO WHERE ID_PAI = 0 ORDER BY ID DESC LIMIT " + TopicoService.TAMANHO_PAGINA + " OFFSET ?";

	public static final String INSERT = "INSERT INTO TOPICO(TEXTO, ID_PAI, DATA, NIVEL) VALUES(?, ?, ?, ?)";

	
	public static final String SELECT_BLACK_LIST = "SELECT ID, PALAVRA FROM BLACKLIST";
	
	public static final String INSERT_BLACK_LIST = "INSERT INTO BLACKLIST(PALAVRA) VALUES(?)";
	
	public static final String DELETE_BLACK_LIST = "DELETE FROM BLACKLIST WHERE ID = ?";
	
}