package br.com.itau.modernizacao.driver;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(keyspace= "modernization", name = "machine_state")
public class StateMachine implements Serializable {
	private static final long serialVersionUID = 1L;

	@PartitionKey
	@Column(name = "transaction_id")
	private UUID transactionId;
	
	@Column(name = "event")
	private Integer event;

	@Column(name = "date_time")
	private Date dateTime;
	
	public UUID getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(UUID transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getEvent() {
		return event;
	}

	public void setEvent(Integer event) {
		this.event = event;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}
