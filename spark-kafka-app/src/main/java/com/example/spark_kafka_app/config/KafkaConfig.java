package com.example.spark_kafka_app.config;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

	/* Kafka 공용 */
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	@Value("${spring.kafka.topic}")
	private String topic;
	
	/* Producer */
	@Value("${spring.kafka.producer.acks}")
	private String acks;
	
	@Value("${spring.kafka.producer.retries}")
	private int retries;
	
	@Value("${spring.kafka.producer.key-serializer}")
	private String keySerializer;
	
	@Value("${spring.kafka.producer.value-serializer}")
	private String valueSerializer;
	
	/* Consumer */
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	

	public String getBootstrapServers() {
		return bootstrapServers;
	}

	public String getTopic() {
		return topic;
	}

	public String getAcks() {
		return acks;
	}

	public int getRetries() {
		return retries;
	}

	public String getKeySerializer() {
		return keySerializer;
	}

	public String getValueSerializer() {
		return valueSerializer;
	}

	public String getGroupId() {
		return groupId;
	}
	
	@Bean
	public KafkaConsumer<String, String> kafkaConsumer() {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        
        return new KafkaConsumer<>(props);
	}
	
	@Bean
	public KafkaProducer<String, String> kafkaProducer() {
		Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all"); // 메시지가 안전하게 전송되도록 설정
        props.put(ProducerConfig.RETRIES_CONFIG, 3); // 실패 시 재시도 횟수 설정
        
        return new KafkaProducer<>(props);
	}
}
