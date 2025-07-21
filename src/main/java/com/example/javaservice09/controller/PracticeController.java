package com.example.javaservice09.controller;

import com.example.javaservice09.service.CloudinaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/practice")
public class PracticeController {

    private static final Logger logger = LoggerFactory.getLogger(PracticeController.class);

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping
    public String uploadImage(MultipartFile file) {
        try {
            String url = cloudinaryService.uploadImage(file);
            logger.info(url);
            return url;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
