package com.chichi.security;

import com.chichi.domain.User;
import com.chichi.service.TokenService;
import com.google.common.base.Optional;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class TokenAuthenticationProvider implements AuthenticationProvider {

    private TokenService tokenService;
    
    public TokenAuthenticationProvider(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<String> token = (Optional) authentication.getPrincipal();
        if (!token.isPresent() || token.get().isEmpty()) {
            throw new BadCredentialsException("Invalid token");
        }
        
        if (!tokenService.isValidToken(token.get())) {
            throw new BadCredentialsException("Invalid token or token expired");
        }
        
        User user = tokenService.getUser(token.get());
        return new AuthenticationWithToken(user, token, null);
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}
