package com.example.spark_kafka_app.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
	
	@Value("${spark.master}")
	private String master;
	
	@Value("${spark.app.name}")
	private String appName;
	
	@Value("${spark.executor.cores}")
	private String executorCores;
	
	@Value("${spark.executor.memory}")
	private String executorMemory;
	
	public String getMaster() {
		return master;
	}
	public String getAppName() {
		return appName;
	}
	public String getExecutorCores() {
		return executorCores;
	}
	public String getExecutorMemory() {
		return executorMemory;
	}
	
	@Bean
	public SparkSession sparkSession() {
		return SparkSession.builder()
				.appName(appName)
				.master(master)
				.getOrCreate();
	}
}
