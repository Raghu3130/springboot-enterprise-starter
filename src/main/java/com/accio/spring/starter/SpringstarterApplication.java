package com.accio.spring.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringstarterApplication {

    /**
     * The main method to execute application.
     *
     * @param args String[]
     */
    public static void main(final String[] args) {
        // SpringApplication.run(SpringstarterApplication.class, args);
        new SpringstarterApplication().run(args);
    }

    /**
     * Private constructor to fix checkstyle error.
     *
     * @param args String[] as passed in main method
     */
    private void run(final String[] args) {
        SpringApplication.run(SpringstarterApplication.class, args);
    }

}
