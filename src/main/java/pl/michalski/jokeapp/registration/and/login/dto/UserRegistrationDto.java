package pl.michalski.jokeapp.registration.and.login.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
public class UserRegistrationDto {

    @NotEmpty
    @Size(min = 3, max = 32, message = "Write 3 - 32 chars")
    private String username;
    @NotEmpty
    @Size(min = 3, max = 32, message = "Write 3 - 32 chars")
    private String password;
    @NotEmpty
    @Size(min = 3, max = 32, message = "Write 3 - 32 chars")
    private String confirmPassword;



}