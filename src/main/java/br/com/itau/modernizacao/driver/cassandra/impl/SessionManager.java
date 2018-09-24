package br.com.itau.modernizacao.driver.cassandra.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;

public class SessionManager {
    private final Logger logger = LoggerFactory.getLogger(SessionManager.class);
    private static SessionManager instance = null;
    private Cluster cluster;
    private Session session;
      
    public static SessionManager getInstance() {
    	if (instance == null) {
    		instance = new SessionManager();
    	}
        return instance;
    }

    public SessionManager() {
    	connect();
    }

    public Session getSession() {
        return this.session;
    }

    private void connect() { 
    	CassandraProperties props = CassandraProperties.getInstance();
    	
    	
        if (logger.isDebugEnabled()) {
            logger.debug("Connecting to Cassandra...");
        }
        cluster = Cluster.builder()
                .addContactPoints(props.getNodes().split(","))
                .withPort(props.getPort())
                .withCredentials(props.getUser(), props.getPassword())
                .withRetryPolicy(DefaultRetryPolicy.INSTANCE)
                .withLoadBalancingPolicy(
                        new TokenAwarePolicy(DCAwareRoundRobinPolicy.builder().build()))
                .build();
        Metadata metadata = getMetadata();
        if (logger.isDebugEnabled()) {
            logger.debug("Connected to cluster: " + metadata.getClusterName());
        }
        for (Host host : metadata.getAllHosts()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Datacenter: " + host.getDatacenter() +
                        ", Host: " + host.getAddress() +
                        ", Rack: " + host.getRack());
            }
        }
        session = cluster.connect();
    }

    public Metadata getMetadata() {
        return cluster.getMetadata();
    }

//    public UserType getUserType(String type) {
//        if (logger.isDebugEnabled()) {
//            logger.debug("Retrieving user type - " + type);
//        }
//        return getMetadata().getKeyspace(keyspace).getUserType(type);
//    }

    public void close() {
        if (logger.isDebugEnabled()) {
            logger.debug("Closing connection...");
        }
        session.close();
        cluster.close();
    }

    protected void finalize() {
        if (logger.isDebugEnabled()) {
            logger.debug("Finalizing the SessionManager...");
        }
        close();
    }
}
