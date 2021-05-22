package com.colatina.sti.service.service.Utils;

import com.colatina.sti.service.service.dto.email.OfferAcepetedEmailDTO;
import com.colatina.sti.service.service.dto.email.WelcomeEmailDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrderQueueSender {

  private final RabbitTemplate rabbitTemplate;

  private final Queue queue;

  public void send(WelcomeEmailDTO email) {
    rabbitTemplate.convertAndSend(this.queue.getName(), email);
  }

  public void send(OfferAcepetedEmailDTO email) {
    rabbitTemplate.convertAndSend(this.queue.getName(), email);
  }
}
