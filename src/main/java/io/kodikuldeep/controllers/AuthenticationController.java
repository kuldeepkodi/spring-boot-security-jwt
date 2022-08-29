package io.kodikuldeep.controllers;

import io.kodikuldeep.model.LoginRequest;
import io.kodikuldeep.model.SignupRequest;
import io.kodikuldeep.model.AuthResponse;
import io.kodikuldeep.security.JwtUtils;
import io.kodikuldeep.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.kodikuldeep.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(setAuthentication(loginRequest));
	}

	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@RequestBody SignupRequest signUpRequest) {
		String username = appUserService.registerUser(signUpRequest);
		return ResponseEntity.ok("New user registered with username : " + username);
	}

	private AuthResponse setAuthentication(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return new AuthResponse(token, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail());
	}

}
