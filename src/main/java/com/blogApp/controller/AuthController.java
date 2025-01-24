package com.blogApp.controller;


import com.blogApp.Dto.LoginDto;
import com.blogApp.Dto.SignUpDto;
import com.blogApp.Dto.UserDto;
import com.blogApp.service.AuthService;
import com.blogApp.service.UserServiceImp;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(path = "/auth")
public class AuthController {
    private final UserServiceImp userService;
    private final AuthService authService;

    public AuthController(UserServiceImp userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUP(@RequestBody SignUpDto signUpDto){
        UserDto userDto = userService.signUP(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response){
        String token = authService.login(loginDto);
        Cookie cookie = new Cookie("token",token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
