package br.SpringWithKafka.Messaging.Config;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.Producer;

public interface MessagingConfigRecord<T extends SpecificRecordBase> {
	
	Producer<String, T> configureProducer();
	
}