package pl.michalski.jokeapp.jokes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.michalski.jokeapp.registration.and.login.model.User;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String value;
    @ManyToOne
    private User user;
}
