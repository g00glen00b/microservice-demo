package be.g00glen00b.service.data;

import javax.persistence.PostPersist;
import be.g00glen00b.service.model.Registration;
import be.g00glen00b.service.utils.BeanUtils;
import org.springframework.messaging.support.MessageBuilder;

public class ProfileRegistrationListener {
    private Registration registration;

    public ProfileRegistrationListener() {
    }

    @PostPersist
    public void register(Profile user) {
        getRegistration().newUserProfileCompleted().send(MessageBuilder.withPayload(user.getEmail()).build());
    }

    private Registration getRegistration() {
        if (registration == null) {
            this.registration = BeanUtils.getBean(Registration.class);
        }
        return registration;
    }
}
