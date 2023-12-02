package com.superngb.userservice.domain;

import com.superngb.userservice.model.UserDtoModel;
import com.superngb.userservice.model.UserPostModel;
import com.superngb.userservice.model.UserUpdateModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserInputBoundary {
    UserDtoModel createUser(UserPostModel userPostModel);
    UserDtoModel getUser(Long id);
    List<UserDtoModel> getUsers();
    UserDtoModel updateUser(UserUpdateModel userUpdateModel);
    UserDtoModel deleteUser(Long id);
    boolean userExists(Long id);
}
