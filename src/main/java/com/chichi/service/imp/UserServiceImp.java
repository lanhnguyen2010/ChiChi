package com.chichi.service.imp;

import com.chichi.domain.User;
import com.chichi.repository.UserRepository;
import com.chichi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lanhnguyen on 26/10/2016.
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserById(String id){
        return userRepository.findByProfileId(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }
}
