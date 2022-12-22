package com.example.beautysalon.controller;


import com.example.beautysalon.dto.BeautyUserDTO;
import com.example.beautysalon.dto.LoggedApplicationUserDTO;
import com.example.beautysalon.dto.LoginModel;
import com.example.beautysalon.dto.RegisterModelDTO;
import com.example.beautysalon.service.BeautyUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    private final BeautyUserService userService;

    public AuthResource(BeautyUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoggedApplicationUserDTO> loginUser(@RequestBody LoginModel loginModel) throws Exception {
        LoggedApplicationUserDTO loggedUser = userService.login(loginModel.getEmail(), loginModel.getPassword());

        return ResponseEntity.ok(loggedUser);
    }

    @PostMapping("/register")
    public ResponseEntity<BeautyUserDTO> register(@RequestBody RegisterModelDTO registerUser) throws Exception {
        BeautyUserDTO registeredUser = userService.register(registerUser);

        return ResponseEntity.ok(registeredUser);
    }
}
