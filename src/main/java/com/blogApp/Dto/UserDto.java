package com.blogApp.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
   @NotEmpty
   @Size(min = 4, message = "username must be min of 4 Charachters ")
    private String name;
    @Email(message = "Please enter valid email")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 10, message = ("password must be min 3 and max 4 charachters"))
    private String password;
//    @NotNull
//    private String about;





}
