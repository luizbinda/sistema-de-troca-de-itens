package com.colatina.sti.service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;

@SpringBootApplication
@EnableRabbit
public class ServiceApplication {

	@Value("${queue.order.name}")
	private String orderQueue;

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@Bean
	public Queue queue() {
		return new Queue(orderQueue, true);
	}

}
