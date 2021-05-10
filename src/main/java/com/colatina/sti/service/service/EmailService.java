package com.colatina.sti.service.service;

import com.colatina.sti.service.service.configuration.ApplicationProperties;
import com.colatina.sti.service.service.dto.email.EmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final ApplicationProperties applicationProperties;

    public void sendEmail(EmailDTO emailDTO){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            message.setTo(emailDTO.getDestinatario());
            message.setFrom(applicationProperties.getEnderecoRemetente(),
                    applicationProperties.getNomeRemetente());
            message.setSubject(emailDTO.getAssunto());

            for(String s : emailDTO.getCopias()){
                message.addCc(s);
            }

            message.setText(emailDTO.getCorpo(), true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
