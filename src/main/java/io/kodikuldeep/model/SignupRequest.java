package io.kodikuldeep.model;

import lombok.Data;

@Data
public class SignupRequest {

	private String username;
	private String email;
	private String password;

}
