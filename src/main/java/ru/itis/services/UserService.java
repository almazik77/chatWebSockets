package ru.itis.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.dto.SignInDto;
import ru.itis.dto.SignUpDto;
import ru.itis.models.Message;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void signUp(SignUpDto form) {

        String rawPassword = form.getPassword();
        String hashPassword = passwordEncoder.encode(rawPassword);


        User user = User.builder()
                .email(form.getEmail())
                .hashPassword(hashPassword)
                .build();

        userRepository.save(user);
    }

    public User signIn(SignInDto signInDto) {

        Optional<User> userCandidate = userRepository.findByEmail(signInDto.getEmail());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            if (passwordEncoder.matches(signInDto.getPassword(), user.getHashPassword())) {
                return user;
            }
        }

        throw new IllegalArgumentException("User not found");

    }


    @SneakyThrows
    public User findUserByEmail(String email) {
        Optional<User> userCandidate = userRepository.findByEmail(email);

        if (userCandidate.isPresent()) {
            return userCandidate.get();
        }
        throw new NameNotFoundException("User not found");
    }

    public User findUser(Long id) {
        Optional<User> userCandidate = userRepository.findOne(id);
        if (userCandidate.isPresent()) {
            return userCandidate.get();
        }
        throw new IllegalArgumentException("User not found");
    }

    public void update(User user) {
        userRepository.update(user);
    }


}
