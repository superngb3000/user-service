package com.superngb.userservice.model;

import com.superngb.userservice.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoModel {
    private Long id;
    private String name;
    private String email;

    public static UserDtoModel mapper(User user){
        return new UserDtoModel(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static List<UserDtoModel> mapper(List<User> userList){
        return userList.stream()
                .map(UserDtoModel::mapper)
                .toList();
    }
}
