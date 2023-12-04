package com.superngb.userservice.controller;

import com.superngb.userservice.domain.UserInputBoundary;
import com.superngb.userservice.model.UserDtoModel;
import com.superngb.userservice.model.UserPostModel;
import com.superngb.userservice.model.UserUpdateModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO переделать все на ResponseEntity
@RestController
@RequestMapping("/")
public class UserController {

    private final UserInputBoundary userInputBoundary;

    public UserController(UserInputBoundary userInputBoundary) {
        this.userInputBoundary = userInputBoundary;
    }

    @PostMapping
    public UserDtoModel postUser(@RequestBody @Valid UserPostModel model){
        return userInputBoundary.createUser(model);
    }

//    @GetMapping("/{id}")
//    public UserDtoModel getUser(@PathVariable Long id){
//        return userInputBoundary.getUser(id);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        UserDtoModel body = userInputBoundary.getUser(id);
        return body == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping
    public List<UserDtoModel> getUsers(){
        return userInputBoundary.getUsers();
    }

    @PutMapping
    public UserDtoModel updateUser(@RequestBody @Valid UserUpdateModel model){
        return userInputBoundary.updateUser(model);
    }

    @DeleteMapping("/{id}")
    public UserDtoModel deleteUser(@PathVariable Long id){
        return userInputBoundary.deleteUser(id);
    }

    @GetMapping("/userExists/{id}")
    boolean userExists(@PathVariable Long id){
        return userInputBoundary.userExists(id);
    }
}
