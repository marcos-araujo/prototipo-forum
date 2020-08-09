package br.com.forum.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.forum.model.Topico;

public class RsTopico {
	
	public static Topico getTopico(ResultSet rs) throws SQLException {
		Topico topico = new Topico();
		topico.setId(rs.getLong("id"));
		topico.setTexto(rs.getString("texto"));
		topico.setIdPai(rs.getLong("id_pai"));
		topico.setNivel(rs.getLong("nivel"));
		Calendar data = Calendar.getInstance();
		data.setTimeInMillis(rs.getTimestamp("data").getTime());
		topico.setData(data);
		return topico;
	}
	
}