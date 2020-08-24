package app.web.pavelk.login5.security;


import app.web.pavelk.login5.model.user.User;
import app.web.pavelk.login5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// совой детеилс сервис
@Service("userDetailsServiceImpl")//конкретный бин реалезует сервис детеилс
public class UserDetailsServiceImpl implements UserDetailsService { // реалезуем сервис

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //ищем юзера
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));

        //создаем спрингового юзера
        return SecurityUser.fromUser(user);
    }
}
