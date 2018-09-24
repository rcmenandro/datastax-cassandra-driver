package br.com.itau.modernizacao.driver.cassandra.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CassandraProperties {
    private final Logger logger = LoggerFactory.getLogger(CassandraProperties.class);
    
	private static final String PROPS = "application.properties";
	private static final String CASSANDRA_NODES = "cassandra.nodes";
	private static final String CASSANDRA_PORT = "cassandra.port";
	private static final String CASSANDRA_USER = "cassandra.user";
	private static final String CASSANDRA_PASSW = "cassandra.password";
	
	private static CassandraProperties instance = null;
	private String nodes;
	private int port;
	private String user;
	private String password;
	
	public static CassandraProperties getInstance() {
		if (instance == null)
			instance = new CassandraProperties();
		return instance;
	}
	
	private CassandraProperties() {
		Properties props = new Properties();
		
		try (InputStream fis = this.getClass().getClassLoader().
				getResourceAsStream(PROPS)) {
			props.load(fis);
			this.nodes = props.getProperty(CASSANDRA_NODES);
			this.port = Integer.parseInt(props.getProperty(CASSANDRA_PORT));
			this.user = props.getProperty(CASSANDRA_USER);
			this.password = props.getProperty(CASSANDRA_PASSW);

		} catch (IOException e) {
			PrintWriter pw = new PrintWriter();
			e.printStackTrace(s);
			logger.error(e.printStackTrace().);
		}
	}
	
	public String getNodes() {
		return nodes;
	}

	public int getPort() {
		return port;
	}
	
	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
}
