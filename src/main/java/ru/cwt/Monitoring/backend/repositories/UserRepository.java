package ru.cwt.Monitoring.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cwt.Monitoring.backend.model.User;


public interface UserRepository extends JpaRepository<User,Long> {


    User findByEmail(String email);
    User findByLogin(String login);


}
