package app.web.pavelk.login4.controller;

import app.web.pavelk.login4.domain.User1;
import app.web.pavelk.login4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User1());
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(
            @ModelAttribute("userForm") @Valid User1 userForm,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }

        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";//главния index.jsp по умолчанию
    }

    @GetMapping("/r2")
    public void registration2() {
        userService.saveR2();
        System.out.println("r2");
    }

    @GetMapping("/r3")
    @ResponseBody
    public User1 registration3() {
        return userService.findUserById(1L);
    }

    @GetMapping("/r4")
    @ResponseBody
    public User1 registration4() {
        return userService.findUserById(10L);
    }
}
