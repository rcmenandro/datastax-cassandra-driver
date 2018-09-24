package br.com.itau.modernizacao.driver;

import java.util.UUID;

import br.com.itau.modernizacao.driver.cassandra.impl.CassandraBaseDao;

public class StateMachineDao extends CassandraBaseDao<StateMachine, UUID> {
	
	public StateMachineDao() {
		super(StateMachine.class);
	}

	@Override
	public void save(StateMachine entity) {
		super.save(entity);
	}
}
