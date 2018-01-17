package ru.cwt.Monitoring.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.cwt.Monitoring.backend.model.User;
import ru.cwt.Monitoring.backend.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService extends CrudService<User> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByEmail(String email) {
        return getRepository().findByEmail(email);
    }
    public User findByLogin(String login) {
        return getRepository().findByLogin(login);
    }


    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }

    @Override
    public long countAnyMatching(Optional<String> filter) {
        return 0;
    }

    @Override
    public User save(User entity) {
        return super.save(entity);
    }

    public User saveNew(User entity){
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.save(entity);
    }

}
