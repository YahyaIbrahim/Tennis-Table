package com.yehia.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Setter
@Getter
public class UserDTO {

    @NotNull
    private String name;

    @NotNull
    @Email(message = "(Error: Please enter a valid email address)")
    private String email;
}
