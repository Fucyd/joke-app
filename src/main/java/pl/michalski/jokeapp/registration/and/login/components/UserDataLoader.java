package pl.michalski.jokeapp.registration.and.login.components;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.michalski.jokeapp.registration.and.login.model.Authority;
import pl.michalski.jokeapp.registration.and.login.model.User;
import pl.michalski.jokeapp.registration.and.login.repository.UserRepository;
import pl.michalski.jokeapp.registration.and.login.repository.AuthorityRepository;

import java.util.HashSet;
@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void loadSecurityData() {
        Authority admin = authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
        Authority moderator = authorityRepository.save(Authority.builder().role("ROLE_MODERATOR").build());
        Authority user = authorityRepository.save(Authority.builder().role("ROLE_USER").build());

        HashSet<Authority> adminSet = new HashSet<>();
        adminSet.add(admin);

        HashSet<Authority> moderatorSet = new HashSet<>();
        adminSet.add(moderator);

        HashSet<Authority> userSet = new HashSet<>();
        adminSet.add(user);

        userRepository.save(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .authorities(adminSet)
                .build()
        );

        userRepository.save(User.builder()
                .username("moderator")
                .password(passwordEncoder.encode("123"))
                .authorities(moderatorSet)
                .build()
        );

        userRepository.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("123"))
                .authorities(userSet)
                .build()
        );
    }

    @Override
    public void run(String... args) throws Exception {
        if (authorityRepository.count() == 0) {
            loadSecurityData();
        }
    }


}