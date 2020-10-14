package pl.michalski.jokeapp.jokes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.michalski.jokeapp.registration.and.login.model.User;
import pl.michalski.jokeapp.registration.and.login.repository.UserRepository;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Component
public class JokesService {
    private UserRepository userRepository;
    private JokeRepository jokeRepository;
    private JokesMapperService jokesMapperService;

    @Autowired
    public JokesService(UserRepository userRepository, JokeRepository jokeRepository, JokesMapperService jokesMapperService) {
        this.userRepository = userRepository;
        this.jokeRepository = jokeRepository;
        this.jokesMapperService = jokesMapperService;
    }

    public List<JokeToShow> getUserSavedJokes(Principal principal) {
        List<JokeToShow> jokesToShow = new ArrayList<>();
        List<Joke> savedJokes = jokeRepository.getAllByUser_Username(principal.getName());
        savedJokes.forEach(joke -> jokesToShow.add(new JokeToShow(joke.getId(), joke.getValue())));
        return jokesToShow;

    }

    //zamiana na obiekt Joke
    public List<JokeToShow> getThreeRandomJokes() throws IOException, InterruptedException {
        List<JokeToShow> jokeToShows = new ArrayList<>();
        List<JokeMapper> jokeMapperList = jokesMapperService.getRandomJokes();
        jokeMapperList.forEach(jokeMapper -> jokeToShows.add(jokeMapperToJoke(jokeMapper)));
        return jokeToShows;
    }

    private JokeToShow jokeMapperToJoke(JokeMapper jokeMapper) {
        JokeToShow jokeToShow = new JokeToShow();
        jokeToShow.setValue(jokeMapper.getValue());
        return jokeToShow;
    }

    public void saveJoke(String username, String jokeValue) {
        Joke joke = new Joke();
        joke.setValue(jokeValue);
        joke.setUser(userRepository.findByUsername(username).get());
        jokeRepository.save(joke);
    }

    public void deleteSavedJoke(Integer id){
        jokeRepository.deleteById(id);
    }

}
