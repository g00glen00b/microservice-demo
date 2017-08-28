package be.g00glen00b.service.data;

import javax.persistence.PostPersist;
import be.g00glen00b.service.model.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class UserRegistrationListener {
    @Autowired
    private Registration registration;

    public UserRegistrationListener(Registration registration) {
        this.registration = registration;
    }

    public UserRegistrationListener() {
    }

    @PostPersist
    public void register(User user) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        registration.newUserUaaCompleted().send(MessageBuilder.withPayload(user.getEmail()).build());
    }
}
