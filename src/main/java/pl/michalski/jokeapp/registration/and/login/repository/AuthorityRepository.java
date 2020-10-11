package pl.michalski.jokeapp.registration.and.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.michalski.jokeapp.registration.and.login.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
