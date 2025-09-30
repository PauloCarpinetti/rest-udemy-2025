package com.github.paulocarpinetti.rest_udemy_2025.services;

import com.github.paulocarpinetti.rest_udemy_2025.data.dto.PersonDTO;
import com.github.paulocarpinetti.rest_udemy_2025.data.dto.config.AccountCredentialsDTO;
import com.github.paulocarpinetti.rest_udemy_2025.data.dto.config.TokenDTO;
import com.github.paulocarpinetti.rest_udemy_2025.exceptions.RequiredObjectIsNullException;
import com.github.paulocarpinetti.rest_udemy_2025.exceptions.UserAlreadyExistsException;
import com.github.paulocarpinetti.rest_udemy_2025.model.Person;
import com.github.paulocarpinetti.rest_udemy_2025.model.User;
import com.github.paulocarpinetti.rest_udemy_2025.repositories.UserRepository;
import com.github.paulocarpinetti.rest_udemy_2025.security.jwt.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.github.paulocarpinetti.rest_udemy_2025.mapper.DozerMapper.parseObject;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    private final Logger logger = LoggerFactory.getLogger(AuthService.class.getName());

    @SuppressWarnings("rawtypes")
    public ResponseEntity signIn(AccountCredentialsDTO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);

            var tokenResponse = new TokenDTO();
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        var user = repository.findByUsername(username);
        var tokenResponse = new TokenDTO();
        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!"); }
        return ResponseEntity.ok(tokenResponse);
    }

    public AccountCredentialsDTO create(AccountCredentialsDTO user) {

        var resultado_consulta = repository.findByUsername(user.getUsername());

        if (resultado_consulta != null)
            throw new UserAlreadyExistsException("Nome de usuario " + resultado_consulta.getUsername()
                    + " ja existe, escolha outro nome!");

        var entity = new User();
        entity.setUserName(user.getUsername());
        entity.setFullName(user.getFullname());
        entity.setPassword(generateHashedPassword(user.getPassword()));
        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
        entity.setEnabled(true);

        try {
            logger.info("Creating new user!");
            var dto = repository.save(entity);
            return new AccountCredentialsDTO(dto.getUsername(), dto.getPassword(), dto.getFullName());
        } catch (Exception e) {
            throw new RequiredObjectIsNullException();
        }
    }

    private String generateHashedPassword(String password) {

        PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8,
                 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

        return passwordEncoder.encode(password);

    }

}
