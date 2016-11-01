package com.chichi.service;

import com.chichi.domain.User;

/**
 * @author  lanhnguyen on 27/10/2016.
 */
public interface UserService {

    User findUserById(String id);
    User findById(Long id);
}
