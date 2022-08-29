package io.kodikuldeep.controllers;

import io.kodikuldeep.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/test")
public class AppUserController {

	@Autowired
	private AppUserService appUserService;

	@GetMapping("/all")
	public ResponseEntity<String> allAccess() {
		return ResponseEntity.ok("Server is up.....");
	}

	@GetMapping("/greeting")
//	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> userAccess() {
		return ResponseEntity.ok(appUserService.greetUser());
	}

}
