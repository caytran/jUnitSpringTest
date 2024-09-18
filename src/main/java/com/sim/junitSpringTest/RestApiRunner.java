package com.sim.junitSpringTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;




/* Packaging as WAR file need this
*/

@SpringBootApplication
public class RestApiRunner extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RestApiRunner.class);
    }

    public static void main(String[] args) {

		SpringApplication.run(RestApiRunner.class, args);
	}

}


//
//@SpringBootApplication
//public class RestApiRunner {
//
//	public static void main(String[] args) {
//
//		SpringApplication.run(RestApiRunner.class, args);
//	}
//
//}
