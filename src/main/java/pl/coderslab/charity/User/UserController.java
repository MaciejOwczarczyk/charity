package pl.coderslab.charity.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "register2";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute @Valid User user, BindingResult result, Model model, @RequestParam String password2) {
        if (!password2.equals(user.getPassword())) {
            model.addAttribute("user", user);
            model.addAttribute("passwordFail", true);
            return "register2";
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register2";
        }

        userRepository.save(user);
        return "redirect:/";
    }


}
