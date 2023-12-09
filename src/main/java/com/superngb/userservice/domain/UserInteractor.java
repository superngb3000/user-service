package com.superngb.userservice.domain;

import com.superngb.userservice.client.BoardServiceClient;
import com.superngb.userservice.client.TaskServiceClient;
import com.superngb.userservice.entity.User;
import com.superngb.userservice.model.ResponseModel;
import com.superngb.userservice.model.UserDtoModel;
import com.superngb.userservice.model.UserPostModel;
import com.superngb.userservice.model.UserUpdateModel;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class UserInteractor implements UserInputBoundary {

    private final UserDataAccess userDataAccess;
    private final BoardServiceClient boardServiceClient;
    private final TaskServiceClient taskServiceClient;

    public UserInteractor(UserDataAccess userDataAccess,
                          BoardServiceClient boardServiceClient,
                          TaskServiceClient taskServiceClient) {
        this.userDataAccess = userDataAccess;
        this.boardServiceClient = boardServiceClient;
        this.taskServiceClient = taskServiceClient;
    }

    @Override
    public ResponseModel<?> createUser(UserPostModel userPostModel) {
        User userByEmail = userDataAccess.findByEmail(userPostModel.getEmail());
        return (userByEmail == null)
                ? ResponseModel.builder().code(201).body(UserDtoModel.mapper(userDataAccess.save(User.builder()
                .name(userPostModel.getName())
                .email(userPostModel.getEmail())
                .password(userPostModel.getPassword()).build()))).build()
                : ResponseModel.builder().code(403).body("This email (" + userPostModel.getEmail() + ") has already been registered").build();
    }

    @Override
    public ResponseModel<?> getUser(Long id) {
        User user = userDataAccess.findById(id);
        return (user == null)
                ? ResponseModel.builder().code(404).body("User with userId = " + id.toString() + " not found").build()
                : ResponseModel.builder().code(200).body(UserDtoModel.mapper(user)).build();
    }

    @Override
    public ResponseModel<?> getUsers() {
        return ResponseModel.builder().code(200).body(UserDtoModel.mapper(userDataAccess.getUsers())).build();
    }

    @Override
    public ResponseModel<?> updateUser(UserUpdateModel userUpdateModel) {
        User userById = userDataAccess.findById(userUpdateModel.getId());
        if (userById == null) {
            return ResponseModel.builder().code(404).body("User with userId = " + userUpdateModel.getId().toString() + " not found").build();
        }
        updateFieldIfNotNull(userUpdateModel.getEmail(), userById::getEmail, userById::setEmail);
        updateFieldIfNotNull(userUpdateModel.getName(), userById::getName, userById::setName);
        updateFieldIfNotNull(userUpdateModel.getPassword(), userById::getPassword, userById::setPassword);
        return ResponseModel.builder().code(200).body(UserDtoModel.mapper(userDataAccess.save(userById))).build();
    }

    private <T> void updateFieldIfNotNull(T newValue, Supplier<T> currentValueSupplier, Consumer<T> updateFunction) {
        T currentValue = currentValueSupplier.get();
        if (newValue != null && (currentValue == null || !Objects.equals(currentValue, newValue))) {
            updateFunction.accept(newValue);
        }
    }

    @Override
    public ResponseModel<?> deleteUser(Long id) {
        User user = userDataAccess.deleteById(id);
        if (user == null) {
            return ResponseModel.builder().code(404).body("User with userId = " + id.toString() + " not found").build();
        }
        boardServiceClient.removeUserFromBoards(id);
        taskServiceClient.removeUserFromTasks(id);
        return ResponseModel.builder().code(200).body(UserDtoModel.mapper(userDataAccess.save(user))).build();
    }
}
