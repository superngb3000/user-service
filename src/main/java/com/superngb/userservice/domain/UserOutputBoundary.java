package com.superngb.userservice.domain;

import com.superngb.userservice.model.UserDtoModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//TODO переделать обертку
public interface UserOutputBoundary {
    UserDtoModel prepareSuccessPostUserView(UserDtoModel model);
    UserDtoModel prepareFailPostUserView();
    UserDtoModel prepareSuccessGetUserView(UserDtoModel model);
    UserDtoModel prepareFailGetUserView();
    UserDtoModel prepareSuccessUpdateUserView(UserDtoModel model);
    UserDtoModel prepareFailUpdateUserView();
    UserDtoModel prepareSuccessDeleteUserView(UserDtoModel model);
    UserDtoModel prepareFailDeleteUserView();
    List<UserDtoModel> convertUser(List<UserDtoModel> modelList);
    boolean prepareUserExistsView();
    boolean prepareUserDoesNotExistView();
}
