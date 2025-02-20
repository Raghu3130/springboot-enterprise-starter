package com.accio.spring.starter.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    /**
     * Logger instance.
     */
    private Logger logger = LoggerFactory.getLogger(PingController.class);

    /**
     * Ping to check server is running or not.
     * always return "PONG"
     *
     * @return String
     */
    @GetMapping("/ping")
    ResponseEntity<String> ping() {
        logger.info("message info");
        logger.warn("message warn");
        logger.error("message error");
        logger.debug("message debug");
        return new ResponseEntity<>("PONG", HttpStatus.OK);
    }

}
