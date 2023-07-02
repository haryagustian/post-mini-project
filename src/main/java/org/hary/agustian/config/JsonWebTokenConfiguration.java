package org.hary.agustian.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hary.agustian.exception.ResourceNotFoundException;
import org.hary.agustian.repository.TokenRepository;
import org.hary.agustian.service.JsonWebTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JsonWebTokenConfiguration extends OncePerRequestFilter {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JsonWebTokenService jsonWebTokenService;

    public JsonWebTokenConfiguration() {
    }

    public JsonWebTokenConfiguration(TokenRepository tokenRepository, UserDetailsService userDetailsService, JsonWebTokenService jsonWebTokenService) {
        this.tokenRepository = tokenRepository;
        this.userDetailsService = userDetailsService;
        this.jsonWebTokenService = jsonWebTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().contains("/api/v1/auth")){
            filterChain.doFilter(request, response);
            throw new ResourceNotFoundException("NOT CONTAINS SERVLET PATH : AUTH");
        }

        final String authenticateHeader = request.getHeader("Authorization");

        if (authenticateHeader == null || !authenticateHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            throw new ResourceNotFoundException("NOT CONTAINS TYPE : BEARER");
        }

        final String jsonWebToken = authenticateHeader.substring(7);
        final String username = jsonWebTokenService.extractUsername(jsonWebToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Boolean validToken = tokenRepository.findByToken(jsonWebToken)
                    .map(token -> !token.isExpired() && !token.isRevoked())
                    .orElse(false);

            if (jsonWebTokenService.isTokenValid(jsonWebToken, userDetails) && validToken){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
