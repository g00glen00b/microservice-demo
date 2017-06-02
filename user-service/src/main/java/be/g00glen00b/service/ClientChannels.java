package be.g00glen00b.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ClientChannels {
    @Output
    MessageChannel output();
}
