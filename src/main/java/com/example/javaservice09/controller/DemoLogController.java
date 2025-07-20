package com.example.javaservice09.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/demo-log")
public class DemoLogController {
    private static final Logger logger = LoggerFactory.getLogger(DemoLogController.class);

    @GetMapping("/trace")
    public String traceLog() {
        logger.trace("Đã ghi log trace");
        return "Ghi log TRACE thành công";
    }

    @GetMapping("/debug")
    public String debugLog() {
        logger.debug("Đã ghi log debug");
        return "Ghi log DEBUG thành công";
    }

    @GetMapping("/info")
    public String infoLog() {
        logger.info("Đã ghi log info");
        return "Ghi log INFO thành công";
    }

    @GetMapping("/warning")
    public String warningLog() {
        logger.warn("Đã ghi log warning");
        return "Ghi log WARNING thành công";
    }

    @GetMapping("/error")
    public String errorLog() {
        logger.error("Đã ghi log error");
        return "Ghi log ERROR thành công";
    }
}
