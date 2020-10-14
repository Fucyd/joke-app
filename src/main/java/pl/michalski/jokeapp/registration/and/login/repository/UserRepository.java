package pl.michalski.jokeapp.registration.and.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.michalski.jokeapp.registration.and.login.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

}
