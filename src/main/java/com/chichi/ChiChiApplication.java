package com.chichi;

import com.chichi.util.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class ChiChiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone(Constants.HCM_TIMEZONE));

		SpringApplication.run(ChiChiApplication.class, args);
	}
}
