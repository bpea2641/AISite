package com.ai.AISite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "/main";
    }
    @GetMapping("/board/save")
    public String boardSave() {
        return "/boardSave";
    }

    @GetMapping("/boardSaveForm")
    public String boardSaveForm() {
        return "/boardSaveForm";
    }
}
