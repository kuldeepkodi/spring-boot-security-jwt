package io.kodikuldeep.service;

import io.kodikuldeep.entity.AppUser;
import io.kodikuldeep.repository.UserRepository;
import io.kodikuldeep.model.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
 * @author Kuldeep Kodi
 *
 */
@Service
public class AppUserServiceImpl implements AppUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String registerUser(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
             throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        AppUser appUser = new AppUser(
                signupRequest.getUsername(), signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword())
        );

        userRepository.save(appUser);
        return signupRequest.getUsername();
    }

    @Override
    public String greetUser() {
        return "User is authenticated!";
    }
}
