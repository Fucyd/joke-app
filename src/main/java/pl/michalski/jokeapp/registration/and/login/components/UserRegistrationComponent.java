package pl.michalski.jokeapp.registration.and.login.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.michalski.jokeapp.registration.and.login.dto.UserRegistrationDto;
import pl.michalski.jokeapp.registration.and.login.model.Authority;
import pl.michalski.jokeapp.registration.and.login.model.User;
import pl.michalski.jokeapp.registration.and.login.repository.AuthorityRepository;
import pl.michalski.jokeapp.registration.and.login.repository.UserRepository;

import java.util.HashSet;

@Component
public class UserRegistrationComponent {
    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserRegistrationComponent(AuthorityRepository authorityRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Boolean checkIfUserExists(String username){
        if(userRepository.findByUsername(username).isEmpty()){
            return true;
        }else {
            return false;
        }

    }

    public void save(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        Authority userRole = authorityRepository.save(Authority.builder().role("ROLE_USER").build());
        HashSet<Authority> authorities = new HashSet<>();
        authorities.add(userRole);
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setAuthorities(authorities);
        userRepository.save(user);
    }


}