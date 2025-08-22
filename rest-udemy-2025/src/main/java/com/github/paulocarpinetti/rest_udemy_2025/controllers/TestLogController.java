package com.github.paulocarpinetti.rest_udemy_2025.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestLogController {

    private final Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @RequestMapping("/test")
    public String testLog(){
        logger.debug("This is a DEBUG log!");
        logger.info("This is a INFO log!");
        logger.warn("This is a WARN log!");
        logger.error("This is a ERROR log!");
        return "Logs generated successfully!";
    }
}
