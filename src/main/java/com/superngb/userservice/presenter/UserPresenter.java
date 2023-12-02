package com.superngb.userservice.presenter;

import com.superngb.userservice.domain.UserOutputBoundary;
import com.superngb.userservice.model.UserDtoModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserPresenter implements UserOutputBoundary {
    @Override
    public UserDtoModel prepareSuccessPostUserView(UserDtoModel model) {
        return model;
    }

    @Override
    public UserDtoModel prepareFailPostUserView() {
        return null;
    }

    @Override
    public UserDtoModel prepareSuccessGetUserView(UserDtoModel model) {
        return model;
    }

    @Override
    public UserDtoModel prepareFailGetUserView() {
        return null;
    }

    @Override
    public UserDtoModel prepareSuccessUpdateUserView(UserDtoModel model) {
        return model;
    }

    @Override
    public UserDtoModel prepareFailUpdateUserView() {
        return null;
    }

    @Override
    public UserDtoModel prepareSuccessDeleteUserView(UserDtoModel model) {
        return model;
    }

    @Override
    public UserDtoModel prepareFailDeleteUserView() {
        return null;
    }

    @Override
    public List<UserDtoModel> convertUser(List<UserDtoModel> modelList) {
        return modelList;
    }

    @Override
    public boolean prepareUserExistsView() {
        return true;
    }

    @Override
    public boolean prepareUserDoesNotExistView() {
        return false;
    }
}
