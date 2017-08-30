package be.g00glen00b.service.data;

import javax.persistence.PostUpdate;
import be.g00glen00b.service.mail.ConfirmationService;
import be.g00glen00b.service.utils.BeanUtils;

public class StateUpdateListener {
    private ConfirmationService confirmationService;

    @PostUpdate
    public void postUpdate(State state) {
        if (state.isProfile() && state.isUaa()) {
            getConfirmationService().sendMail(state);
        }
    }

    private ConfirmationService getConfirmationService() {
        if (confirmationService == null) {
            confirmationService = BeanUtils.getBean(ConfirmationService.class);
        }
        return confirmationService;
    }
}
