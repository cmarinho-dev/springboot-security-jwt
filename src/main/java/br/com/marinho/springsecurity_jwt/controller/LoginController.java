package br.com.marinho.springsecurity_jwt.controller;

import br.com.marinho.springsecurity_jwt.dto.Login;
import br.com.marinho.springsecurity_jwt.dto.Session;
import br.com.marinho.springsecurity_jwt.model.User;
import br.com.marinho.springsecurity_jwt.repository.UserRepository;
import br.com.marinho.springsecurity_jwt.security.JwtCreator;
import br.com.marinho.springsecurity_jwt.security.JwtObject;
import br.com.marinho.springsecurity_jwt.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public Session signIn(@RequestBody Login login) {
        User user = repository.findByUsername(login.getUsername());
        if (user!=null) {
            boolean passwordOk = encoder.matches(login.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Incorrect password for user '"+ user.getUsername() +"'");
            }
            Session session = new Session();
            session.setLogin(user.getUsername());

            JwtObject jwtObject = new JwtObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION));
            jwtObject.setRoles(user.getRoles());

            session.setToken(JwtCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return session;
        } else {
            throw new RuntimeException("Error trying to log in");
        }
    }
}
