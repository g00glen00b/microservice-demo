package be.g00glen00b.service.security.service;

import be.g00glen00b.service.security.model.TokenUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String getUsername() {
        if (SecurityContextHolder.getContext() != null &&
            SecurityContextHolder.getContext().getAuthentication() != null &&
            SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof TokenUserDetails) {
            return ((TokenUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        } else {
            return null;
        }
    }
}
