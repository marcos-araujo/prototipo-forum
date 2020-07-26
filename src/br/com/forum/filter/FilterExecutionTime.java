package br.com.forum.filter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.forum.jdbc.ConnectionFactory;

@WebFilter("/*")
public class FilterExecutionTime implements Filter{
	
    private static final Logger logger = LogManager.getLogger(FilterExecutionTime.class);
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
		try{
			Connection connection = new ConnectionFactory().getConnection();
			request.setAttribute("connection", connection);
			
			chain.doFilter(request, response);
			connection.close();
		}catch(SQLException | URISyntaxException e){
			logger.error(e);
		}
	}

	@Override
	public void destroy(){
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException{
	}

}