package com.ai.AISite.controller;

import com.ai.AISite.dto.UserDto;
import com.ai.AISite.entity.UserEntity;
import com.ai.AISite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";  // Thymeleaf 템플릿 이름
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";  // Thymeleaf 템플릿 이름
    }

    // 회원가입 요청 처리
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute UserDto userDto, Model model) {
        UserEntity registeredUser = userService.registerUser(userDto);
        if (registeredUser != null) {
            return "redirect:/user/login";  // 회원가입 후 로그인 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "Registration failed");
            return "signup";  // 오류 발생 시 회원가입 페이지로 다시 이동
        }
    }

    // 로그인 요청 처리
    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserDto userDto, Model model) {
        Optional<UserEntity> userEntityOptional = userService.loginUser(userDto.getUserEmail(), userDto.getUserPassword());
        if (userEntityOptional.isPresent()) {
            return "redirect:/";  // 로그인 후 메인 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";  // 로그인 실패 시 로그인 페이지로 다시 이동
        }
    }
}
