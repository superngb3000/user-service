package com.superngb.userservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserPostModel {
    @NotBlank
    private String name;
    @NotNull
    @Email
    private String email;
    @NotBlank
    private String password;
}
