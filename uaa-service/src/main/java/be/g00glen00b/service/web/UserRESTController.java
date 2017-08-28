package be.g00glen00b.service.web;

import be.g00glen00b.service.model.TokenUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
public class UserRESTController {

    @GetMapping()
    public TokenUserDetails getUser(@AuthenticationPrincipal TokenUserDetails principal) {
        return principal;
    }
}
