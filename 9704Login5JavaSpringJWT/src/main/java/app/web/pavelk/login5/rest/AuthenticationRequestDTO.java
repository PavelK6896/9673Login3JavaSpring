package app.web.pavelk.login5.rest;

import lombok.Data;

@Data
public class AuthenticationRequestDTO { //для джексона
    private String email;
    private String password;
}
