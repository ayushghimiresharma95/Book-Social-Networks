package com.example.BookNetworkServer.Email;

import org.eclipse.angus.mail.handlers.multipart_mixed;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;


@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender ;
    private final SpringTemplateEngine springTemplateEngine ;

    @Async
    public void sendMail(
        String to,
        String username,
        EmailTemplateName emailTemplate, 
        String ActivationURL,
        String activationCode, 
        String subject
     ) throws MessagingException{


        String templateString ;

        
        if (emailTemplate == null ){
            templateString = "confirmEmail" ;
        }
        else{
            templateString = emailTemplate.name() ;
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage() ;
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED, UTF_8.name()) ;

        Map<String,Object> properties = new HashMap<>() ;
        properties.put("username", username) ;
        properties.put("confirmationURL", ActivationURL) ;
        properties.put("activation_code", activationCode) ;

        Context context = new Context() ;
        context.setVariables(properties);

        helper.setTo(to);
        helper.setFrom("contact@aliboucoding.com") ;
        helper.setSubject(subject);

        String template = springTemplateEngine.process(templateString,context) ;
        helper.setText(template, true);
        javaMailSender.send(mimeMessage) ;

    }
}
