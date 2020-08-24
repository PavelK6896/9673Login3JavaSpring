package app.web.pavelk.login1.configs;

import app.web.pavelk.login1.service.OidcUserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private OidcUserService1 oidcUserService1;

    @Autowired
    public void setCustomOidcUserService(OidcUserService1 oidcUserService1) {
        this.oidcUserService1 = oidcUserService1;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .oidcUserService(oidcUserService1);
    }


//    @Bean // сразу можем создать пользователя
//    ApplicationListener<AuthenticationSuccessEvent> doSomething() {
//        return event1 -> {
//            System.out.println("name " + event1.getAuthentication().getName());
//            event1.getAuthentication().getPrincipal();
//            ObjectMapper mapper = new ObjectMapper();
//          //  Person person = mapper.readValue(jsonString, Person.class);
//            // парсить json или отправить в сервис
//            System.out.println(event1.getAuthentication().getPrincipal());
//        };
//    }

//    @Bean
//    public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo) {
//        return map -> {
//            String id = (String) map.get("sub");
//            User user = userDetailsRepo.findById(id).orElseGet(() -> {
//                User newUser = new User();
//                newUser.setId(id);
//                newUser.setName((String) map.get("name"));
//                newUser.setEmail((String) map.get("email"));
//                newUser.setGender((String) map.get("gender"));
//                newUser.setLocale((String) map.get("locale"));
//                newUser.setUserpic((String) map.get("picture"));
//
//                return newUser;
//            });
//            user.setLastVisit(LocalDateTime.now());
//            return userDetailsRepo.save(user);
//        };
//    }

}