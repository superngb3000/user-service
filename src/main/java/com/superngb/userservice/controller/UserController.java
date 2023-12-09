package com.superngb.userservice.controller;

import com.superngb.userservice.domain.UserInputBoundary;
import com.superngb.userservice.model.ResponseModel;
import com.superngb.userservice.model.UserPostModel;
import com.superngb.userservice.model.UserUpdateModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class UserController {

    private final UserInputBoundary userInputBoundary;

    public UserController(UserInputBoundary userInputBoundary) {
        this.userInputBoundary = userInputBoundary;
    }

    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody @Valid UserPostModel model) {
        ResponseModel<?> response = userInputBoundary.createUser(model);
        return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        ResponseModel<?> response = userInputBoundary.getUser(id);
        return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        ResponseModel<?> response = userInputBoundary.getUsers();
        return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserUpdateModel model) {
        ResponseModel<?> response = userInputBoundary.updateUser(model);
        return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        ResponseModel<?> response = userInputBoundary.deleteUser(id);
        return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getCode()));
    }
}
