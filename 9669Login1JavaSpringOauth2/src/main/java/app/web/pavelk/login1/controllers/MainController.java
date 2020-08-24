package app.web.pavelk.login1.controllers;


import app.web.pavelk.login1.service.OidcUserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class MainController {

    private final OidcUserService1 oidcUserService1;

    @Autowired
    public MainController(OidcUserService1 oidcUserService1) {
        this.oidcUserService1 = oidcUserService1;
    }

    @GetMapping
    public String greeting(Principal principal) {

        System.out.println(principal.getName());
        System.out.println(oidcUserService1.getUserCache().getUsername());
        System.out.println(oidcUserService1.getUserCache().getEmail());

        return "greeting.html";
    }

}
