package com.openapi.codegen.service;

import com.openapi.codegen.SwaggerCodgen.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {


    private static Map<Long, User> USER_MAP = new HashMap<>();

    public void addUser(User user) {
        USER_MAP.putIfAbsent(user.getId(), user);
    }

    public List<User> getUsers() {
        return new ArrayList<>(USER_MAP.values());
    }

    public User getUser(Long userId) {
        return USER_MAP.getOrDefault(userId, new User());
    }
}
