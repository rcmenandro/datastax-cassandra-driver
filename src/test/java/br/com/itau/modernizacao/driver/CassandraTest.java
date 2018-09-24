package br.com.itau.modernizacao.driver;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;

import junit.framework.TestCase;

public class CassandraTest extends TestCase {
	
	@Test
	public void test() {
		StateMachine sm = new StateMachine();
		StateMachineDao dao = new StateMachineDao();
		sm.setTransactionId(UUID.randomUUID());
		sm.setEvent(1);
		sm.setDateTime(new Date());
		dao.save(sm);
		dao.getSession().close();
	}
}
