package ru.shaldnikita.Tags.backend.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.shaldnikita.Tags.backend.model.User;


public interface UserRepository extends JpaRepository<User,Long> {


    User findByEmail(String email);
    User findByLogin(String login);


}
