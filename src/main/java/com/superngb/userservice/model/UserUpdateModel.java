package com.superngb.userservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateModel {
    @NotNull
    private Long id;
    private String name;
    @Email
    private String email;
    private String password;
}
