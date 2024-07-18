package com.django.it.shoppingapp.dtos.requests;

import com.django.it.shoppingapp.model.Role;
import com.django.it.shoppingapp.validation.StrongPassword;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterRequest {


    @NotBlank(message = "firstname is required")
    private String firstname;
    @NotBlank(message = "lastname is required")
    private String lastname;
    @NotBlank(message = "email is required")
    @Email(message = "email format is not valid")
    private String email;
    @NotBlank(message = "password is required")
    @StrongPassword
    private String password;
/*    @NotNull
    private Role role;*/
}
