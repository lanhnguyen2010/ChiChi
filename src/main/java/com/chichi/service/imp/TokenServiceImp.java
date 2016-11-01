package com.chichi.service.imp;

import com.chichi.domain.User;
import com.chichi.service.TokenService;
import com.chichi.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static com.chichi.util.Constants.SECRETS_FILENAME;

/**
 * @author lanhnguyen on 31/10/2016.
 */
public class TokenServiceImp implements TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImp.class);

    @Autowired
    private UserService userService;

    private GoogleIdTokenVerifier verifier;

    public TokenServiceImp() {
        HttpTransport httpTransport = null;
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            GoogleClientSecrets clientSecrets = null;
            clientSecrets = GoogleClientSecrets.load(jsonFactory,
                    new InputStreamReader(TokenServiceImp.class.getResourceAsStream(SECRETS_FILENAME)));
            verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                    .setAudience(Collections.singletonList(clientSecrets.getWeb().getClientId()))
                    .build();
        } catch (IOException | GeneralSecurityException e) {
            LOGGER.error("Token Service: ", e);
        }

    }

    @Override
    public boolean isValidToken(String idToken) {
        LOGGER.info("Validate token: " + verifyToken(idToken));
        return verifyToken(idToken) != null;
    }

    @Override
    public User getUser(String idToken) {
        GoogleIdToken googleIdToken = verifyToken(idToken);
        User user = null;
        if (googleIdToken != null && googleIdToken.getPayload() != null) {
            GoogleIdToken.Payload payload = googleIdToken.getPayload();
            user = userService.findUserById(payload.getSubject());
        }
        return user;
    }

    private GoogleIdToken verifyToken(String idToken) {
        try {
            return verifier.verify(idToken);
        } catch (GeneralSecurityException | IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return null;
    }
}
