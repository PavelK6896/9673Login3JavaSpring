package app.web.pavelk.login5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }

}
