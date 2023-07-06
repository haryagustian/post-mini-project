package org.hary.agustian.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
        log.info("CALL::COMPONENT::CONSTRUCTOR::JsonWebTokenConfiguration");
        this.tokenRepository = tokenRepository;
        this.userDetailsService = userDetailsService;
        this.jsonWebTokenService = jsonWebTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("CALL::COMPONENT::OVERRIDE::doFilterInternal");
        if (request.getServletPath().contains("/api/v1/auth")){
            filterChain.doFilter(request, response);
            log.info("CALL::COMPONENT::OVERRIDE::getServletPath::doFIlter");
            return;
//            throw new ResourceNotFoundException("NOT CONTAINS SERVLET PATH : AUTH");
        }

        final String authenticateHeader = request.getHeader("Authorization");

        if (authenticateHeader == null || !authenticateHeader.startsWith("Bearer ")){
            log.info("CALL::COMPONENT::authenticateHeader::isNULL");
            filterChain.doFilter(request, response);
            return;
//            throw new ResourceNotFoundException("NOT CONTAINS TYPE : BEARER");
        }

        final String jsonWebToken = authenticateHeader.substring(7);
        final String username = jsonWebTokenService.extractUsername(jsonWebToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            log.info("CALL::COMPONENT::OVERRIDE::username::if::Statement");
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Boolean validToken = tokenRepository.findByToken(jsonWebToken)
                    .map(token -> !token.isExpired() && !token.isRevoked())
                    .orElse(false);

            if (jsonWebTokenService.isTokenValid(jsonWebToken, userDetails) && validToken){
                log.info("CALL::COMPONENT::OVERRIDE::jsonWebTokenService::if::isTokenValid");
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        log.info("CALL::COMPONENT::filterChain::doFilter::request::response");
        filterChain.doFilter(request, response);
    }
}
