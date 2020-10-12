package pl.michalski.jokeapp.jokes;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Component
public class JokesService {

    private JokeRepository jokeRepository;
    private JokesMapperService jokesMapperService;

    @Autowired
    public JokesService(JokeRepository jokeRepository, JokesMapperService jokesMapperService) {
        this.jokeRepository = jokeRepository;
        this.jokesMapperService = jokesMapperService;
    }

    public List<Joke> getUserSavedJokes(Principal principal){
        return jokeRepository.getAllByUser_Username(principal.getName());
    }

    //zamiana na obiekt Joke
    public List<JokeDto> getThreeRandomJokes() throws IOException, InterruptedException {
        List<JokeDto> jokeDtos = new ArrayList<>();
        List<JokeMapper> jokeMapperList = jokesMapperService.getRandomJokes();
        jokeMapperList.forEach(jokeMapper -> jokeDtos.add(jokeMapperToJoke(jokeMapper)));
        return jokeDtos;
    }

    private JokeDto jokeMapperToJoke(JokeMapper jokeMapper){
        JokeDto jokeDto = new JokeDto();
        jokeDto.setValue(jokeMapper.getValue());
        return jokeDto;
    }

}
