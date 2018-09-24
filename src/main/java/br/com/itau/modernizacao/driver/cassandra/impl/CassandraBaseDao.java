package br.com.itau.modernizacao.driver.cassandra.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import br.com.itau.modernizacao.driver.BaseDao;

public abstract class CassandraBaseDao<T, PK extends Serializable> implements BaseDao<T, Serializable> {
	private final Logger logger = LoggerFactory.getLogger(CassandraBaseDao.class);
	protected final Session session;
	protected final Mapper<T> mapper;
	
	public CassandraBaseDao(final Class<T> persistentClass) {
		session = SessionManager.getInstance().getSession();
		mapper = new MappingManager(session).mapper(persistentClass);
	}
	
	public Session getSession() {
		return this.session;
	}
	
	public T get(Serializable id) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Retrieving entity with id: %s", id));
		}
		return mapper.get(id);
	}

	public boolean exists(Serializable id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Retrieving entity with id: " + id);
		}
		return (mapper.get(id) != null);
	}

	public void save(T entity) {
		if (logger.isDebugEnabled()) {
			logger.debug("Saving entity...");
		}
		mapper.save(entity);

	}

	public void update(T entity) {
		if (logger.isDebugEnabled()) {
			logger.debug("Saving entity...");
		}
		mapper.save(entity);
	}

	public void remove(T entity) {
		if (logger.isDebugEnabled()) {
			logger.debug("Removing entity from database...");
		}
		mapper.delete(entity);
	}

	public void remove(Serializable id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Removing entity with id: " + id + " from database...");
		}
		mapper.delete(id);
	}

}
