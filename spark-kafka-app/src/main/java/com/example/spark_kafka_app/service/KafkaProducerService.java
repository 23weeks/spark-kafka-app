package com.example.spark_kafka_app.service;

import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import com.example.spark_kafka_app.dto.MessageType;

@Service
public class KafkaProducerService {
	
	private final KafkaProducer<String, String> producer;
	
	public KafkaProducerService(KafkaProducer<String, String> producer) {
		this.producer = producer;
	}
	
	public void sendMessage(MessageType type, String message) {
		String topic;
		
		switch (type) {
			case TEST:
				topic = "test-topic";
				break;
			default:
				throw new IllegalArgumentException("Unknown message type: " + type);
		}
		
		String key = UUID.randomUUID().toString();
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, message);
		producer.send(record, (metadata, exception) -> {
			if(exception == null) {
				System.out.println("Message sent successfully: " + metadata);
			} else {
				exception.printStackTrace();
			}
		});
	}
}
