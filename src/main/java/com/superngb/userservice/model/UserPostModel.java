package com.superngb.userservice.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserPostModel {
    private String name;
    private String email;
    private String password;
}
