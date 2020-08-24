package app.web.pavelk.login1.service;

import app.web.pavelk.login1.model.User1;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Service
@SessionAttributes("user")
public class OidcUserService1 extends OidcUserService {
    private User1 getUserCache;

    public User1 getUserCache() {
        return getUserCache;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest); // загружаем даные пльзователя
        ////////////////
        Map<String, Object> attributes = oidcUser.getAttributes();
        attributes.forEach((o, z) -> {
            System.out.println(o + " // " + z);
        });
        System.out.println(attributes.size());
        // проверяем по базе
        // записать в базу
        try {
            getUserCache = new User1();
            getUserCache.setUsername((String) attributes.get("name"));
            getUserCache.setEmail((String) attributes.get("email"));
            return oidcUser;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }
}