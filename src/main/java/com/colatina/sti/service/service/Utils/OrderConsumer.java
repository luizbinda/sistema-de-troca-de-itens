package com.colatina.sti.service.service.Utils;

import com.colatina.sti.service.service.EmailService;
import com.colatina.sti.service.service.dto.email.EmailDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrderConsumer {

  private final EmailService emailService;

  @RabbitListener(queues = {"${queue.order.name}"})
  public void receive(@Payload EmailDTO email) {
    emailService.sendEmail(email);
  }
}
