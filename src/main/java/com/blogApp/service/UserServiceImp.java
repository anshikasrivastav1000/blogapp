package com.blogApp.service;

import com.blogApp.Dto.SignUpDto;
import com.blogApp.Dto.UserDto;
import com.blogApp.entity.User;
import com.blogApp.exception.ResourceNotFoundException;
import com.blogApp.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserServiceImp implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepo.findByEmail(username).orElseThrow(() -> new BadCredentialsException("user with this email not found" + username));
    }

    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }


    public UserDto updateUser(UserDto userDto, Long userId) {
       User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found With ID : " + userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;

    }


    public UserDto getUserById(Long userId) {
         User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found By Id" + userId));
         return userToDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return  userDtos;
    }


    public void deleteUser(Long userId) {
        User user= this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found By Id" + userId) );
        this.userRepo.delete(user);

    }


    public UserDto signUP(SignUpDto signUpDto) {
        Optional<User> user = userRepo.findByEmail(signUpDto.getEmail());
        if(user.isPresent()){
            throw  new BadCredentialsException("user with this email already exits"+signUpDto.getEmail());
        }
        User toBeCreatedUser = modelMapper.map(signUpDto,User.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));
        User saveUser = userRepo.save(toBeCreatedUser);
        return modelMapper.map(saveUser,UserDto.class);
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
