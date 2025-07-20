package com.example.javaservice09.controller;

import com.example.javaservice09.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class Bai5Controller {
    private static final Logger logger = LoggerFactory.getLogger(Bai5Controller.class);

    private static final Map<Long, User> users = Map.of(
            1L, new User(1L, "Alice"),
            2L, new User(2L, "Bob")
    );

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        if (!users.containsKey(id)) {
            logger.error("Không tìm thấy người dùng với ID: {}", id);
            return ResponseEntity.status(404).body("User not found");
        }

        logger.info("Trả về thông tin người dùng với ID: {}", id);
        return ResponseEntity.ok(users.get(id));
    }
}
