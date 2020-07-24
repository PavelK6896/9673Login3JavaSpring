package app.web.pavelk.login1.model;

import lombok.Data;

import java.util.Set;

@Data
public class User1 {
    private Long id;

    private String username;

    private String email;

    private String password;

    private Set<Role> roles;
}
