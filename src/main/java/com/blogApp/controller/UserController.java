package com.blogApp.controller;

import com.blogApp.Dto.UserDto;
import com.blogApp.entity.User;
import com.blogApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    //POST create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }
    // Put update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
      UserDto updateUser =  this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updateUser);

    }

    //Delete
    @DeleteMapping("/{userId}")
    public  ResponseEntity<Void> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return  ResponseEntity.noContent().build();
    }


    //get all user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return  ResponseEntity.ok(this.userService.getAllUsers());
    }
    //get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        UserDto userDto = userService.getUserById(userId);
        return  ResponseEntity.ok(userDto);
    }



}
