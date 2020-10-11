package pl.michalski.jokeapp.registration.and.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.michalski.jokeapp.registration.and.login.components.UserRegistrationComponent;
import pl.michalski.jokeapp.registration.and.login.dto.UserRegistrationDto;

import javax.validation.Valid;

@Controller
public class LoginController {
    private UserRegistrationComponent userRegistrationComponent;
//    private EmailService emailService = new EmailServiceImpl();

    @Autowired
    public LoginController(UserRegistrationComponent userRegistrationComponent) {
        this.userRegistrationComponent = userRegistrationComponent;
    }

    @GetMapping("/")
    public String mainPage(){
        return "main-page";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("newUser", userRegistrationDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute("newUser") @Valid UserRegistrationDto userRegistrationDto,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if(!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())){
            bindingResult.rejectValue("password", "differentPasswords", "Passwords must match");
            return "registration";

        }
        if(!userRegistrationComponent.checkIfUserExists(userRegistrationDto.getUsername(), userRegistrationDto.getEmail())){
            bindingResult.rejectValue("username", "usernameNotAvailable");
            bindingResult.rejectValue("email", "emailNotAvailable");
            return "registration";

        }

        userRegistrationComponent.save(userRegistrationDto);

        return "redirect:/login";
    }
}
