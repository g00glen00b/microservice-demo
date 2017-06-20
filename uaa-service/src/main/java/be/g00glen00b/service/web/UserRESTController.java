package be.g00glen00b.service.web;

import be.g00glen00b.service.model.TokenUserDetails;
import be.g00glen00b.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
public class UserRESTController {
    private UserService userService;

    @Autowired
    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public TokenUserDetails getUser(@AuthenticationPrincipal TokenUserDetails principal) {
        return principal;
    }

    @PostMapping()
    public String saveUser(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
        return userService.save(email, username, password);
    }
}
