package xyz.dedsecm.icar.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import xyz.dedsecm.icar.dto.UserDTO;
import xyz.dedsecm.icar.service.UserService;

@Component("userSecurity")
public class UserSecurity {

    private final UserService userService;

    public UserSecurity(UserService userService) {
        this.userService = userService;
    }

    public boolean isOwner(Authentication authentication, Long id) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof Jwt) {
            Jwt jwt = (Jwt) principal;
            String username = jwt.getSubject();
            UserDTO user = userService.getUserByUsername(username);
            return user != null && id.equals(user.getId());
        }
        return false;
    }
}

