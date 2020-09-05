package br.com.forum.jdbc;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {
	
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);
	
    public Connection getConnection() throws URISyntaxException, IOException{
		String username;
		String forname;
		String password;
		String url;

		try {

			if (System.getenv("DATABASE_URL") != null) {
				URI dbUri = new URI(System.getenv("DATABASE_URL"));
				forname = "org.postgresql.Driver";
				username = dbUri.getUserInfo().split(":")[0];
				password = dbUri.getUserInfo().split(":")[1];
				url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
			} else {
				Properties properties = new Properties();
				properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
				forname = properties.get("database.server.forname").toString();
				username = properties.get("database.server.user").toString();
				password = properties.get("database.server.password").toString();
				url = properties.get("database.server.url").toString();
			}
		
			Class.forName(forname);
			return DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			logger.error(e);
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			logger.error(e);
		}
		
		return null;

	}

}