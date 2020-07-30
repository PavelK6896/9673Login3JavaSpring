package app.web.pavelk.login4.service;

import app.web.pavelk.login4.domain.Role1;
import app.web.pavelk.login4.domain.User1;
import app.web.pavelk.login4.repo.RoleRepository;
import app.web.pavelk.login4.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override//авторизация
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(s, UserDetails.class);
        System.out.println(user + " user");
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean saveUser(User1 user) {//1 add
        User1 userFromDB = userRepository.findByUsername(user.getUsername(), User1.class);
        System.out.println(userFromDB);
        if (userFromDB != null) {//если пользователь есть
            return false;
        }
        // user.setRoles(Collections.singleton(roleRepository.findByName("ROLE_USER")));
        user.setRoles(Collections.singleton(roleRepository.findByName("ROLE_ADMIN")));

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public void saveR2() {//new admin and role
        if (roleRepository.findAll().size() < 1) {
            roleRepository.save(new Role1("ROLE_USER"));
            roleRepository.save(new Role1("ROLE_ADMIN"));
        }

        User1 user = new User1();
        user.setUsername("a");
        user.setPassword(bCryptPasswordEncoder.encode("1"));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    public boolean deleteUser(Long userId) {//2
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User1> allUsers() {//3
        return userRepository.findAll();
    }

    public List<User1> userGtList(Long idMin) {//4 one user criteria api
        return em.createQuery("SELECT u FROM User1 u WHERE u.id > :paramId", User1.class)
                .setParameter("paramId", idMin).getResultList();
    }
    public User1 findUserById(Long userId) {//5 one user nativeQuery
        Optional<User1> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User1());
    }
}
