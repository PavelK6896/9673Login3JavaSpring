package app.web.pavelk.login5.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.header}")
    private String authorizationHeader;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    //наша реализация интерфейса но не с нашим именем б
    public JwtTokenProvider(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct // просто шифруем короткую строку и получаеться много символов
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //создаем токен кладем роль и имя
    public String createToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username); // создаеться по имени
        claims.put("role", role);// кладется по ключю
        Date now = new Date(); //дата создания
        Date validity = new Date(now.getTime() + validityInMilliseconds * 1000); // дата экспирации

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now) // текущая
                .setExpiration(validity) // экспирация
                .signWith(SignatureAlgorithm.HS256, secretKey) //алгоритм и ключь
                .compact();
    }

    public boolean validateToken(String token) { // проверка токена
        try {
            //парсим токен по ключю
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            //проверка что до текущей даты
            return !claimsJws.getBody().getExpiration().before(new Date());

        } catch (JwtException | IllegalArgumentException e) {
            // если токен взломан
            throw new JwtAuthenticationException("JWT token is expired or invalid", HttpStatus.UNAUTHORIZED);//НЕАВТОРИЗОВАННЫЙ 401
        }
    }

    // получить спринговую аут
    public Authentication getAuthentication(String token) {

        // ищем токен в базе принемаем спрингового усера, нашу реализацию спрингового интерфейса но через интерфейс вобщем так
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));


        // создаем спринговую аутонтетификацию
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //парсим токен и получаем имя
    public String getUsername(String token) {

        //getSubject это усер наме вот так
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // из запроса получаем токен
    public String resolveToken(HttpServletRequest request) { //разрешить
        return request.getHeader(authorizationHeader);
    }
}
