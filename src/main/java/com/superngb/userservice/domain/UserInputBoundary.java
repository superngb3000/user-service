package com.superngb.userservice.domain;

import com.superngb.userservice.model.ResponseModel;
import com.superngb.userservice.model.UserPostModel;
import com.superngb.userservice.model.UserUpdateModel;
import org.springframework.stereotype.Component;


@Component
public interface UserInputBoundary {
    ResponseModel<?> createUser(UserPostModel userPostModel);

    ResponseModel<?> getUser(Long id);

    ResponseModel<?> getUsers();

    ResponseModel<?> updateUser(UserUpdateModel userUpdateModel);

    ResponseModel<?> deleteUser(Long id);
}
