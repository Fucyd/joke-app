package pl.michalski.jokeapp.jokes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class JokesMapperService {
    private final static String API_URL =  "https://api.chucknorris.io/jokes/random";

    public List<JokeMapper> getRandomJokes() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        List<JokeMapper> jokesMapper = new ArrayList<>();

        for(int x =0; x<3; x++) {
            HttpResponse<String> response = getStringJokeHttpResponse(httpClient);
            ObjectMapper objectMapper = new ObjectMapper();
            JokeMapper jokeMapper = objectMapper.readValue(response.body(), new TypeReference<JokeMapper>() {});
            jokesMapper.add(jokeMapper);
        }
        return jokesMapper;
    }

    private HttpResponse<String> getStringJokeHttpResponse(HttpClient httpClient) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(API_URL))
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }


}
