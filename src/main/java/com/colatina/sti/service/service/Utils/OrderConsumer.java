package com.colatina.sti.service.service.Utils;

import com.colatina.sti.service.service.EmailService;
import com.colatina.sti.service.service.dto.email.OfferAcepetedEmailDTO;
import com.colatina.sti.service.service.dto.email.WelcomeEmailDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrderConsumer {

  private final EmailService emailService;

  @RabbitListener(queues = {"${queue.order.name}"})
  public void receive(@Payload WelcomeEmailDTO email) {
    emailService.sendEmail(email);
  }

  @RabbitListener(queues = {"${queue.order.name}"})
  public void receive(@Payload OfferAcepetedEmailDTO email) {
    emailService.sendEmail(email);
  }
}
