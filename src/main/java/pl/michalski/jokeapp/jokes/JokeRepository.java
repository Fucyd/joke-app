package pl.michalski.jokeapp.jokes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JokeRepository extends JpaRepository<Joke,Integer> {
    List<Joke> getAllByUser_Username(String username);
}
