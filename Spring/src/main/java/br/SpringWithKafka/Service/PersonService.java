package br.SpringWithKafka.Service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.SpringWithKafka.DTO.CommonDTO;
import br.SpringWithKafka.DTO.PersonDTO;
import br.SpringWithKafka.Messaging.MessagingRecord;
import br.SpringWithKafka.avro.person.Person;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService implements MessagingRecord<Person> {
	
	
	private final Producer<String, Person> producer;

	@Override
	public String topic() {
		return "person";
	}
	
	@Autowired
	public PersonService(@Qualifier("personProducer") Producer<String, Person> producer) {
		this.producer = producer;
	}

	@Override
	public ProducerRecord<String, Person> createProducerRecord(Person person) {
		return new ProducerRecord<String, Person>(this.topic(), person);
	}

	@Override
	public void send(CommonDTO personDTO) {
		Person person = 
				Person.newBuilder()
				.setFirstName(((PersonDTO) personDTO).getFirstName())
				.setLastName(((PersonDTO) personDTO).getLastName())
				.setFiscalNumber(((PersonDTO) personDTO).getFiscalNumber())
				.build();
		
		
		producer.send(this.createProducerRecord(person), (rm, ex) -> {
			if (ex == null) {
				log.info("Data sent with success!!!");
			} else {
				log.error("Fail to send message", ex);
			}
		});

		producer.flush();
		producer.close();
		
	}


}
