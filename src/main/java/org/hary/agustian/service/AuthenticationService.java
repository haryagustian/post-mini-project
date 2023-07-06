package org.hary.agustian.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hary.agustian.entity.Token;
import org.hary.agustian.entity.User;
import org.hary.agustian.exception.ResourceNotFoundException;
import org.hary.agustian.model.auth.AuthenticationRequest;
import org.hary.agustian.model.auth.AuthenticationResponse;
import org.hary.agustian.model.user.UserRequest;
import org.hary.agustian.repository.TokenRepository;
import org.hary.agustian.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JsonWebTokenService jsonWebTokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    private void saveUserToken(User user, String token) {
        tokenRepository.save(new Token(token, false, false, user));
    }

    private void revokeAllUserTokens(User user) {
        List<Token> allValidTokenByUser = tokenRepository.findAllValidTokenByUser(user.getId());

        if (allValidTokenByUser.isEmpty())
            throw new ResourceNotFoundException("REVOKE ALL USER TOKEN : VALID TOKEN USER EMPTY");
//            return;
        allValidTokenByUser.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(allValidTokenByUser);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//            return;
            throw new ResourceNotFoundException("AUTHENTICATION HEADER BEARER : NULL");
        }
        final String refreshToken = authHeader.substring(7);
        final String username = jsonWebTokenService.extractUsername(refreshToken);

        if (username != null) {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("REFRESH TOKEN : USER NAME "));

            if (jsonWebTokenService.isTokenValid(refreshToken, user)) {
                var accessToken = jsonWebTokenService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                new ObjectMapper().writeValue(response.getOutputStream(), new AuthenticationResponse(accessToken, refreshToken));
            }
        }
    }

    public AuthenticationResponse register(UserRequest userRequest) {
        User user = new User(
                userRequest.getFullname(),
                userRequest.getUsername(),
                userRequest.getEmail(),
                passwordEncoder.encode(userRequest.getPassword()),
                userRequest.getSex(),
                userRequest.getTelephone(),
                userRequest.getAddress(),
                userRequest.getRole()
        );
        User save = userRepository.save(user);
        String generatedToken = jsonWebTokenService.generateToken(user);
        String generatedRefreshToken = jsonWebTokenService.generateRefreshToken(user);
        saveUserToken(save, generatedToken);
        return new AuthenticationResponse(generatedToken, generatedRefreshToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        passwordEncoder.encode(authenticationRequest.getPassword())
                )
        );
        User user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(() -> new ResourceNotFoundException("AUTHENTICATE : USER NAME"));
        String generatedToken = jsonWebTokenService.generateToken(user);
        String generatedRefreshToken = jsonWebTokenService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, generatedToken);
        return new AuthenticationResponse(generatedToken, generatedRefreshToken);

    }
}
