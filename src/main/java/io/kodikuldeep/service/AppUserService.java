package io.kodikuldeep.service;

import io.kodikuldeep.model.SignupRequest;

/*
 * @author Kuldeep Kodi
 *
 */
public interface AppUserService {

    String registerUser(SignupRequest signupRequest);

    String greetUser();

}
