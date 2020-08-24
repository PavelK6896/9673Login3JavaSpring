package app.web.pavelk.login5.security.jwt;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter // исключения для испорченого токена
public class JwtAuthenticationException extends AuthenticationException {
    private HttpStatus httpStatus;
    public JwtAuthenticationException(String msg) {
        super(msg);
    }
    public JwtAuthenticationException(String msg, HttpStatus httpStatus) { // конструкто со статусом 401 так
        super(msg);
        this.httpStatus = httpStatus;
    }
}
