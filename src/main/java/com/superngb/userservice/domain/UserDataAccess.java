package com.superngb.userservice.domain;

import com.superngb.userservice.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDataAccess {
    User save(User user);
    User findById(Long id);
    User findByEmail(String email);
    List<User> getUsers();
    User deleteById(Long id);
}
