package com.example.spark_kafka_app.service;

import java.util.Collections;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
	private final SparkSession spark;
	
	public KafkaConsumerService(SparkSession spark) {
		this.spark = spark;
	}
	
	@KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(ConsumerRecord<String, String> record) {
		String message = record.value();
		logger.info("Kafka message consumed: {}");
		
		Dataset<String> dataset = spark.createDataset(
				Collections.singletonList(message),
				Encoders.STRING()
		);
		
		Dataset<Row> result = dataset
				.flatMap((String line) -> java.util.Arrays.asList(line.split(" ")).iterator(), Encoders.STRING())
				.groupBy("value")
				.count();
		
		result.show();
	}
}
