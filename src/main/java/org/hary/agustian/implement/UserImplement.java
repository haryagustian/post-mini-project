package org.hary.agustian.implement;

import org.hary.agustian.enums.Role;
import org.hary.agustian.enums.Sex;
import org.hary.agustian.model.user.UserRequest;
import org.hary.agustian.model.user.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface UserImplement {
    ResponseEntity<Map<String, Object>> inquiry(String fullname, String username, String email, String password, Sex sex, String telephone, String address, Role role, Integer page, Integer size, String[] sort);
    ResponseEntity<Map<String, Object>> createUser(UserRequest userRequest);
    ResponseEntity<Map<String, Object>> updateUser(Integer id, UserRequest userRequest);
    ResponseEntity<HttpStatus> deleteUser(Integer id);
}
