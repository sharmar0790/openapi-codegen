package com.openapi.codegen.controller;


import com.openapi.codegen.SwaggerCodgen.api.UserApi;
import com.openapi.codegen.SwaggerCodgen.model.User;
import com.openapi.codegen.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController implements UserApi {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> addUser(User user) {
        LOG.info("Saving user details {}", user);
        try {
            userService.addUser(user);
            return ResponseEntity.ok("UserAdded Successfully");
        } catch (Exception e) {
            LOG.error("Error {} caught while persisting the user into database {}", e.getMessage(), user);
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        LOG.info("Loaded users details {}", users);
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> getUser(Long userId) {
        User user = userService.getUser(userId);
        LOG.info("Loaded user details {}", user);
        return ResponseEntity.ok(user);
    }
}
