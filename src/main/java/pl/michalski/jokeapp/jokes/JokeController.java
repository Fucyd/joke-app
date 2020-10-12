package pl.michalski.jokeapp.jokes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class JokeController {

    private JokesService jokesService;
    @Autowired
    public JokeController(JokesService jokesService) {
        this.jokesService = jokesService;
    }

    @GetMapping("/jokes")
    public String getUserSavedJokes(Model model) throws IOException, InterruptedException {
         model.addAttribute("jokes",jokesService.getThreeRandomJokes());



         return "random-jokes";
    }

}
