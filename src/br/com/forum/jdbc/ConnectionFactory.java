package br.com.forum.jdbc;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {
	
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);
	
    public Connection getConnection() throws URISyntaxException, IOException{
    	URI dbUri = new URI(System.getenv("DATABASE_URL"));

    	String forname = "org.postgresql.Driver";
    	String username = dbUri.getUserInfo().split(":")[0];
    	String password = dbUri.getUserInfo().split(":")[1];
    	String url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        
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