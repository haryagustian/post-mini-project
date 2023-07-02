package org.hary.agustian.service;

import org.hary.agustian.entity.User;
import org.hary.agustian.enums.Role;
import org.hary.agustian.enums.Sex;
import org.hary.agustian.exception.ResourceNotFoundException;
import org.hary.agustian.implement.UserImplement;
import org.hary.agustian.model.user.UserRequest;
import org.hary.agustian.model.user.UserResponse;
import org.hary.agustian.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Service
public class UserService implements UserImplement {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Map<String, Object>> inquiry(
            @RequestParam(required = false) String fullname,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) Sex sex,
            @RequestParam(required = false) String telephone,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "3") Integer size,
            @RequestParam(required = false, defaultValue = "id, desc") String[] sort
    ) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Object>> insertUser(UserRequest userRequest) {
        try {
            User user = new User();
            if (userRequest.getFullname() != null)
                user.setFullname(userRequest.getFullname());

            if (userRequest.getUsername() != null) {
                User usernameIsExists = userRepository.findByUsername(userRequest.getUsername()).orElse(null);
                if (usernameIsExists != null){
                    throw new ResourceNotFoundException("username already exists");
                }
                user.setUsername(userRequest.getUsername());
            }
            if (userRequest.getEmail() != null) {
                User usernameIsExists = userRepository.findByEmail(userRequest.getEmail()).orElse(null);
                if (usernameIsExists != null){
                    throw new ResourceNotFoundException("email already exists");
                }
                user.setEmail(userRequest.getEmail());
            }

            if (userRequest.getPassword() != null)
                user.setPassword(userRequest.getPassword());

            if (userRequest.getSex() != null)
                user.setSex(userRequest.getSex());

            if (userRequest.getTelephone() != null)
                user.setTelephone(userRequest.getTelephone());

            if (userRequest.getAddress() != null)
                user.setAddress(userRequest.getAddress());

            if (userRequest.getRole() != null)
                user.setRole(userRequest.getRole());

            if (userRequest.getTokens() != null)
                user.setTokens(userRequest.getTokens());

            if (userRequest.getBorrower() != null)
                user.setBorrower(userRequest.getBorrower());

            userRepository.save(user);
            UserResponse userResponse = new UserResponse(user.getId(), user.getFullname(), user.getUsername(), user.getEmail(), user.getPassword(), user.getSex(), user.getTelephone(), user.getAddress(), user.getRole(), user.getTokens(), user.getBorrower());

            return new ResponseEntity<>(userResponse,HttpStatus.CREATED);

        }catch (Exception exception){
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateUser(Integer id, UserRequest userRequest) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteUser(Integer id) {
        return null;
    }
}
