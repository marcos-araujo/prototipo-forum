package br.com.forum.jdbc;

import java.io.IOException;
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
    	Properties properties = new Properties();
    	properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
    	String forname	= properties.get("database.server.forname").toString();
    	String username = properties.get("database.server.user").toString();
    	String password = properties.get("database.server.password").toString();
    	String url = properties.get("database.server.url").toString();
        
		try{
			Class.forName(forname);
			return DriverManager.getConnection(url, username, password);
		}catch(SQLException e){
			logger.error(e);
			throw new RuntimeException(e);
		}catch(ClassNotFoundException e){
			logger.error(e);
		}
		return null;
	}

}