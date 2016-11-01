package com.chichi.resource;

import com.chichi.domain.User;
import com.chichi.service.imp.UserServiceImp;
import com.chichi.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lanhnguyen on 26/10/2016.
 */

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    UserServiceImp userService;

    @RequestMapping(value = "/getUserByGoogleId", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByGoogleId(@RequestParam String userId) {
        if (userService.findUserById(userId) != null) {
            return ResponseEntity.ok(userService.findUserById(userId));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@RequestParam Long userId) {
        if (userService.findById(userId) != null) {
            return ResponseEntity.ok(userService.findById(userId));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public ResponseEntity getCurrentUser() {
        User user = SecurityUtil.getCurrentUser();
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }


}
