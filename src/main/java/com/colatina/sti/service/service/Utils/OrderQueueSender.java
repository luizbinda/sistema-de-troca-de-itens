package com.colatina.sti.service.service.Utils;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrderQueueSender {

  private final RabbitTemplate rabbitTemplate;

  private final Queue queue;

  public void send(String order) {
    rabbitTemplate.convertAndSend(this.queue.getName(), order);
  }
}
