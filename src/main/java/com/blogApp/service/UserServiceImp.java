package com.blogApp.service;

import com.blogApp.Dto.UserDto;
import com.blogApp.entity.User;
import com.blogApp.exception.ResourceNotFoundException;
import com.blogApp.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
       User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found With ID : " + userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;

    }

    @Override
    public UserDto getUserById(Integer userId) {
         User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found By Id" + userId));
         return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return  userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user= this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found By Id" + userId) );
        this.userRepo.delete(user);

    }

    private User dtoToUser(UserDto userDto){
        User user;
        user = this.modelMapper.map(userDto,User.class);
        return  user;
    }
    private UserDto userToDto(User user){
        UserDto userDto;
        userDto = this.modelMapper.map(user,UserDto.class);

        return userDto;
    }
}
