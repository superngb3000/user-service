package com.superngb.userservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserPostModel {
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String password;
}
