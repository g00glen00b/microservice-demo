package be.g00glen00b.service.mail;

import be.g00glen00b.service.data.State;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class ConfirmationService {
    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;
    private String loginUrl;

    public ConfirmationService(JavaMailSender mailSender, TemplateEngine templateEngine, @Value("${app.login.url}") String loginUrl) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.loginUrl = loginUrl;
    }

    public void sendMail(State state) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(state.getEmail());
            messageHelper.setSubject("Blog application registration");
            messageHelper.setText(build(state), true);
        };
        mailSender.send(preparator);
    }

    private String build(State state) {
        Context context = new Context();
        context.setVariable("username", state.getUsername());
        context.setVariable("loginUrl", loginUrl);
        return templateEngine.process("welcome", context);
    }
}
