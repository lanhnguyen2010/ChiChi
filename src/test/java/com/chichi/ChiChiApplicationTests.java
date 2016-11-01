package com.chichi;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class ChiChiApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Test
	public void testParseIdToken() throws GeneralSecurityException, IOException {
		String idTokenString = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImE4OGVjMjM3ZDYzMmJiMTJiMDgxNzRjNjY4Y2RkMmMzOGNlNzJiNWIifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDc3OTg2OTI4LCJleHAiOjE0Nzc5OTA1MjgsImF0X2hhc2giOiJ5Q3JjeHVpZFd6QmJRRWQweXZUcVFBIiwiYXVkIjoiNDY5MDg4NzM2MzUwLXY5cnMzZmxpZXNiZjZkcWY3ZXZxZHMxbzdqbnE4MDg0LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTAxNjExODI2MTIwMzAyMTkwMjA1IiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF6cCI6IjQ2OTA4ODczNjM1MC12OXJzM2ZsaWVzYmY2ZHFmN2V2cWRzMW83am5xODA4NC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImVtYWlsIjoibmd1eWVua2ltbGFuaDIwMTBAZ21haWwuY29tIiwibmFtZSI6IkxhbmggTmd1eWVuIEtpbSIsInBpY3R1cmUiOiJodHRwczovL2xoNC5nb29nbGV1c2VyY29udGVudC5jb20vLXJHT1liaURoNGtnL0FBQUFBQUFBQUFJL0FBQUFBQUFBQ0lnLzhFc3JPTmNrN3ZzL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJMYW5oIiwiZmFtaWx5X25hbWUiOiJOZ3V5ZW4gS2ltIiwibG9jYWxlIjoidmkifQ.A3AGkb57kj1B14iWg1Vzl0ERKB5cC8C8BykoRpPB5XM6_RXcaY3t4MaWiHKtvkQcTRYAhDq5nWix_DdysX4ROt1yfio81py_salsj078vwIXW2CPR2gsuYP1J38XSLb9rUyfEnmymDUdbtcGj2cG73RcO51_dYxH19fhu-hs0YOH97IfbY7wy0jK7vyacgrCmIwr0lFNANPaZn7llYiZzMSEQj-fmYoQbzGLBo3sSv2KadQ4jB2iT6qmb6RCt9HhwpoZkHjdjDyX3btxUqi3Cnrw_4mPnLKdqeYtsqfj1ra8rmIsVbp1KbBT3FXXrdE8g14G2fhhz_hmpgixMXd4UA";
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
				new InputStreamReader(ChiChiApplicationTests.class.getResourceAsStream("/chichi.json")));
		System.out.println(clientSecrets.getWeb().getClientId());
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singletonList(clientSecrets.getWeb().getClientId()))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        //idToken.getPayload().setExpirationTimeSeconds((new Date().getTime())/1000);

		System.out.println(idToken.getPayload().getEmail());
        System.out.println(idToken.getPayload().getExpirationTimeSeconds());
	}

}
