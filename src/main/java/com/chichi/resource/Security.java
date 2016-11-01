package com.chichi.resource;

import com.chichi.domain.User;
import com.chichi.repository.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author lanhnguyen on 07/08/2016.
 */

@RestController
public class Security {

    private static final Logger LOGGER = Logger.getLogger(Security.class);
    public static final String CLIENT_ID = "469088736350-v9rs3fliesbf6dqf7evqds1o7jnq8084.apps.googleusercontent.com";
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/api/authenticate", method = RequestMethod.GET)
    public ResponseEntity authenticate(@RequestParam(value = "idToken") String idTokenString) throws IOException, GeneralSecurityException {

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier(new NetHttpTransport(),
                JacksonFactory.getDefaultInstance());

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {

            GoogleIdToken.Payload payload = idToken.getPayload();

            String userId = payload.getSubject();

            System.out.println("User ID: " + userId);
            User newUser = new User();
            newUser.setProfileId(userId);
            newUser.setFirstName((String) payload.get("family_name"));
            newUser.setFullName((String) payload.get("name"));
            newUser.setLastName((String) payload.get("given_name"));
            newUser.setEmail(payload.getEmail());
            newUser.setPictureUrl((String) payload.get("picture"));
            User oldUser = userRepository.findByProfileId(userId);
            if (oldUser == null) {
                LOGGER.info("new User : " + userId);
            } else {
                LOGGER.info("old User : " + userId);
                newUser.setId(oldUser.getId());

            }
            newUser = userRepository.save(newUser);
            return ResponseEntity.ok(newUser);


        } else {
            LOGGER.warn("Invalid ID token.");
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/api/test", method = RequestMethod.GET)
    public ResponseEntity testApi() {
        return ResponseEntity.ok("API test");

    }
}
