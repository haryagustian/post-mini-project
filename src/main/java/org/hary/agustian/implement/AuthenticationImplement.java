package org.hary.agustian.implement;

import org.hary.agustian.model.auth.AuthenticationRequest;
import org.hary.agustian.model.user.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthenticationImplement {
    ResponseEntity<Map<String, Object>> createUser(UserRequest userRequest);
}
