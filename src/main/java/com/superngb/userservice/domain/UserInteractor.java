package com.superngb.userservice.domain;

import com.superngb.userservice.entity.User;
import com.superngb.userservice.model.UserDtoModel;
import com.superngb.userservice.model.UserPostModel;
import com.superngb.userservice.model.UserUpdateModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class UserInteractor implements UserInputBoundary {

    private final UserDataAccess userDataAccess;
    private final UserOutputBoundary userOutputBoundary;

    public UserInteractor(UserDataAccess userDataAccess, UserOutputBoundary userOutputBoundary) {
        this.userDataAccess = userDataAccess;
        this.userOutputBoundary = userOutputBoundary;
    }

    @Override
    public UserDtoModel createUser(UserPostModel userPostModel) {
        if (userPostModel == null
                || userPostModel.getName() == null
                || userPostModel.getEmail() == null
                || userPostModel.getPassword() == null) {
            return userOutputBoundary.prepareFailPostUserView();
        }
        User userByEmail = userDataAccess.findByEmail(userPostModel.getEmail());
        return (userByEmail == null)
                ? userOutputBoundary.prepareSuccessPostUserView(UserDtoModel.mapper(
                userDataAccess.save(User.builder()
                        .name(userPostModel.getName())
                        .email(userPostModel.getEmail())
                        .password(userPostModel.getPassword()).build())))
                : userOutputBoundary.prepareFailPostUserView();
    }

    @Override
    public UserDtoModel getUser(Long id) {
        User user = userDataAccess.findById(id);
        return (user == null)
                ? userOutputBoundary.prepareFailGetUserView()
                : userOutputBoundary.prepareSuccessGetUserView(UserDtoModel.mapper(user));
    }

    @Override
    public List<UserDtoModel> getUsers() {
        return userOutputBoundary.convertUser(UserDtoModel.mapper(userDataAccess.getUsers()));
    }

    @Override
    public UserDtoModel updateUser(UserUpdateModel userUpdateModel) {
        User userById = userDataAccess.findById(userUpdateModel.getId());
        if (userById == null) {
            return userOutputBoundary.prepareFailUpdateUserView();
        }
        updateFieldIfNotNull(userUpdateModel.getEmail(), userById::getEmail, userById::setEmail);
        updateFieldIfNotNull(userUpdateModel.getName(), userById::getName, userById::setName);
        updateFieldIfNotNull(userUpdateModel.getPassword(), userById::getPassword, userById::setPassword);
        return userOutputBoundary.prepareSuccessUpdateUserView(UserDtoModel.mapper(userDataAccess.save(userById)));
    }

    private <T> void updateFieldIfNotNull(T newValue, Supplier<T> currentValueSupplier, Consumer<T> updateFunction) {
        T currentValue = currentValueSupplier.get();
        if (newValue != null && (currentValue == null || !Objects.equals(currentValue, newValue))) {
            updateFunction.accept(newValue);
        }
    }

    //TODO убирать пользователей из списков в board и task
    @Override
    public UserDtoModel deleteUser(Long id) {
        User user = userDataAccess.deleteById(id);
        return (user == null)
                ? userOutputBoundary.prepareFailDeleteUserView()
                : userOutputBoundary.prepareSuccessDeleteUserView(UserDtoModel.mapper(user));
    }
}
