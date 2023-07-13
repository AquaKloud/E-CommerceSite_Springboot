package com.aquakloud.ECommerce.controller;

import com.aquakloud.ECommerce.dto.ResponseDTO;
import com.aquakloud.ECommerce.dto.user.SignInDTO;
import com.aquakloud.ECommerce.dto.user.SignInResponseDTO;
import com.aquakloud.ECommerce.dto.user.SignupDTO;
import com.aquakloud.ECommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // two apis

    //signup
    @PostMapping("/signup")
    public ResponseDTO signup(@RequestBody SignupDTO signupDTO){
        return userService.signUp(signupDTO);
    }

    //signin

    @PostMapping("/signin")
    public SignInResponseDTO signIn(@RequestBody SignInDTO signInDTO){
        return userService.signIn(signInDTO);
    }


}
