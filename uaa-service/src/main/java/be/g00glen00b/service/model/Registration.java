package be.g00glen00b.service.model;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Registration {
    @Input("new-user-request")
    SubscribableChannel newUserRequest();

    @Input("new-user-uaa-completed")
    MessageChannel newUserUaaCompleted();
}
