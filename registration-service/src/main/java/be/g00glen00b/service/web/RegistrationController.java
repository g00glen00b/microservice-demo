package be.g00glen00b.service.web;

import be.g00glen00b.service.data.StateService;
import be.g00glen00b.service.web.model.StateDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class RegistrationController {
    private StateService service;

    public RegistrationController(StateService service) {
        this.service = service;
    }

    @PostMapping
    public StateDTO save(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
        return service.save(email, username, password);
    }
}
