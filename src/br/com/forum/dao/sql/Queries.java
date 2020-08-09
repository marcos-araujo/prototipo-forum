package br.com.forum.dao.sql;

public class Queries {
	
	public static final String SELECT = "SELECT ID, TEXTO, ID_PAI, DATA, NIVEL FROM TOPICO WHERE ID = ?";
	
	public static final String SELECT_PERGUNTAS = "SELECT ID, TEXTO, ID_PAI, DATA, NIVEL FROM TOPICO WHERE ID_PAI = 0";

	public static final String SELECT_RESPOSTAS = "SELECT ID, TEXTO, ID_PAI, DATA, NIVEL FROM TOPICO WHERE ID_PAI = ?";

	public static final String SELECT_COUNT = "SELECT COUNT(*) AS QUANTIDADE FROM TOPICO WHERE ID_PAI = 0";
	
	public static final String SELECT_PAGINA_PERGUNTAS = "SELECT ID, TEXTO, ID_PAI, DATA, NIVEL FROM TOPICO WHERE ID_PAI = 0 ORDER BY ID DESC LIMIT 10 OFFSET ?";

	public static final String INSERT = "INSERT INTO TOPICO(TEXTO, ID_PAI, DATA, NIVEL) VALUES(?, ?, ?, ?)";

	
	
}
