package pl.michalski.jokeapp.jokes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JokeMapper {

    private List<String> categories;
    private SimpleDateFormat created_at;
    private String icon_url;
    private String id;
    private SimpleDateFormat updated_at;
    private String url;
    private String value;

}