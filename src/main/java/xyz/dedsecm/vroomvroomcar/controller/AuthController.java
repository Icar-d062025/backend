package xyz.dedsecm.vroomvroomcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xyz.dedsecm.vroomvroomcar.dto.UserDTO;
import xyz.dedsecm.vroomvroomcar.model.User;
import xyz.dedsecm.vroomvroomcar.repository.UserRepository;
import xyz.dedsecm.vroomvroomcar.security.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public String login(@RequestBody UserDTO loginDto) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(loginDto.getUsername()))
                .findFirst().orElse(null);

        if (user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return tokenService.generateToken(user.getUsername(), user.getRole());
        }

        return "Invalid credentials";
    }
}