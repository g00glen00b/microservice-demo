package be.g00glen00b.service.data;

import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import be.g00glen00b.service.EmailTakenException;
import be.g00glen00b.service.StateNotFoundException;
import be.g00glen00b.service.UsernameTakenException;
import be.g00glen00b.service.model.NewUser;
import be.g00glen00b.service.model.Registration;
import be.g00glen00b.service.web.model.StateDTO;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class StateService {
    private StateRepository repository;
    private Registration registration;

    public StateService(StateRepository repository, Registration registration) {
        this.repository = repository;
        this.registration = registration;
    }

    @Transactional
    public StateDTO save(String email, String username, String password) {
        repository.findOneByEmail(email).ifPresent(state -> {throw new EmailTakenException("Email is already taken");});
        repository.findOneByUsername(username).ifPresent(state -> {throw new UsernameTakenException("Username is already taken");});
        State state = repository.saveAndFlush(new State(email, username, LocalDateTime.now()));
        AfterCommitTransactionSynchronization synchronization = () -> registration
            .newUserRequest()
            .send(MessageBuilder.withPayload(new NewUser(email, username, password)).build());
        TransactionSynchronizationManager.registerSynchronization(synchronization);
        return StateDTO.fromEntity(state);
    }

    @Transactional
    public State updateUaa(String email, boolean uaa) {
        State state = repository.findOneByEmail(email).orElseThrow(StateNotFoundException::new);
        state.setUaa(uaa);
        return state;
    }

    @Transactional
    public State updateProfile(String username, boolean profile) {
        State state = repository.findOneByUsername(username).orElseThrow(StateNotFoundException::new);
        state.setProfile(profile);
        return state;
    }
}
