package com.colatina.sti.service.service;

import com.colatina.sti.service.service.Utils.ConstantsUtils;
import com.colatina.sti.service.service.configuration.ApplicationProperties;
import com.colatina.sti.service.service.dto.email.EmailDTO;
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

            Map model = new HashMap();
            model.put("user", emailDTO.getUserName());
            String template = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, emailDTO.getTemplate(), model);
            message.setText(template, true);
            javaMailSender.send(mimeMessage);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
