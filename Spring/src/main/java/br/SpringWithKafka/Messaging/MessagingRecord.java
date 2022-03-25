package br.SpringWithKafka.Messaging;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.ProducerRecord;

import br.SpringWithKafka.DTO.CommonDTO;

public interface MessagingRecord<T extends SpecificRecordBase> {
	
	String topic();
	
	ProducerRecord<String, T> createProducerRecord(T type);
	
	void send(CommonDTO personDTO);
}
