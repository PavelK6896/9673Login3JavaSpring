package app.web.pavelk.login2.controllers;

import app.web.pavelk.login2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/unsecured")
    public String usecuredPage() {
        return "unsecured";
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/authenticated")
    public String authenticatedPage(Principal principal) {
        System.out.println(principal.getName());
        String out = String.format("authenticated user: %s, password: %s", principal.getName(), "1");
        return out;
    }

    @GetMapping("/admin")
    public String adminPage() {
        userService.n1();
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }
}
