package com.example.spark_kafka_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spark_kafka_app.dto.MessageRequest;
import com.example.spark_kafka_app.service.KafkaProducerService;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {
	
	private final KafkaProducerService kafkaProducerService;
	
	public KafkaController(KafkaProducerService kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
	}
	
	@PostMapping("/send")
	public ResponseEntity<String> sendMessage(@RequestBody MessageRequest request) {
		kafkaProducerService.sendMessage(request.getType(), request.getMessage());
		return ResponseEntity.ok("Message sent to Kafka!");
	}
}
