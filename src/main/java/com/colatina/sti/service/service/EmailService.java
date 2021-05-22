package com.colatina.sti.service.service;

import com.colatina.sti.service.service.configuration.ApplicationProperties;
import com.colatina.sti.service.service.dto.email.OfferAcepetedEmailDTO;
import com.colatina.sti.service.service.dto.email.WelcomeEmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.apache.velocity.app.VelocityEngine;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;


@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final ApplicationProperties applicationProperties;
    public final VelocityEngine velocityEngine;

    public void sendEmail(WelcomeEmailDTO welcomeEmailDTO){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(welcomeEmailDTO.getDestinatario());
            message.setFrom(applicationProperties.getEnderecoRemetente(),
                    applicationProperties.getNomeRemetente());
            message.setSubject(welcomeEmailDTO.getAssunto());

            for(String s : welcomeEmailDTO.getCopias()){
                message.addCc(s);
            }

            Map model = new HashMap();
            model.put("user", welcomeEmailDTO.getUserName());
            String template = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, welcomeEmailDTO.getTemplate(), model);
            message.setText(template, true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEmail(OfferAcepetedEmailDTO welcomeEmailDTO){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(welcomeEmailDTO.getDestinatario());
            message.setFrom(applicationProperties.getEnderecoRemetente(),
                    applicationProperties.getNomeRemetente());
            message.setSubject(welcomeEmailDTO.getAssunto());

            for(String s : welcomeEmailDTO.getCopias()){
                message.addCc(s);
            }

            Map model = new HashMap();
            model.put("user", welcomeEmailDTO.getUserName());
            String template = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, welcomeEmailDTO.getTemplate(), model);
            message.setText(template, true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
