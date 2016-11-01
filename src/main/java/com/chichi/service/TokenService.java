package com.chichi.service;

import com.chichi.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author  lanhnguyen on 31/10/2016.
 */
@Service
public interface TokenService {

    boolean isValidToken(String idToken);

    User getUser(String idToken);

}
