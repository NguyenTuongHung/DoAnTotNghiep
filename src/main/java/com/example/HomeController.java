package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Khi người dùng truy cập localhost:8080/ hoặc localhost:8080/index
    @GetMapping({"/", "/index"})
    public String home() {
        return "index"; // Nó sẽ tự động tìm đến file templates/index.html
    }

    // Khi người dùng truy cập localhost:8080/login
    @GetMapping("/login")
    public String login() {
        return "login"; // Nó sẽ tự động tìm đến file templates/login.html
    }

    // Khi người dùng truy cập localhost:8080/register
    @GetMapping("/register")
    public String register() {
        return "register"; // Nó sẽ tự động tìm đến file templates/register.html
    }
}