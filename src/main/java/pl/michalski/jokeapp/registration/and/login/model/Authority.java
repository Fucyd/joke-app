package pl.michalski.jokeapp.registration.and.login.model;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String role;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}
