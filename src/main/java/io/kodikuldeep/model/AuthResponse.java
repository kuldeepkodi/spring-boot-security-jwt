package io.kodikuldeep.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

	private String token;
	private Long id;
	private String username;
	private String email;

}
