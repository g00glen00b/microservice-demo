package be.g00glen00b.service.stream;

import javax.annotation.PostConstruct;
import be.g00glen00b.service.data.StateService;
import be.g00glen00b.service.model.Registration;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener {
    private Registration registration;
    private StateService stateService;

    public RegistrationListener(Registration registration, StateService stateService) {
        this.registration = registration;
        this.stateService = stateService;
    }


    @PostConstruct
    public void handleEvents() {
        registration.newUserUaaCompleted().subscribe(message -> stateService.updateUaa((String) message.getPayload(), true));
        registration.newUserProfileCompleted().subscribe(message -> stateService.updateProfile((String) message.getPayload(), true));
    }

}
