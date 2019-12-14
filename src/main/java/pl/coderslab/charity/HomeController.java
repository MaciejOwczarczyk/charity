package pl.coderslab.charity;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Authorities.Authorities;
import pl.coderslab.charity.Authorities.AuthoritiesRepository;
import pl.coderslab.charity.Category.CategoryRepository;
import pl.coderslab.charity.Donation.DonationRepository;
import pl.coderslab.charity.Insitution.Institution;
import pl.coderslab.charity.Insitution.InstitutionRepository;
import pl.coderslab.charity.User.User;
import pl.coderslab.charity.User.UserRepository;
import pl.coderslab.charity.ConfirmationToken.ConfirmationToken;
import pl.coderslab.charity.ConfirmationToken.ConfirmationTokenRepository;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final JavaMailSender javaMailSender;


    public HomeController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository, AuthoritiesRepository authoritiesRepository,
                          ConfirmationTokenRepository confirmationTokenRepository, JavaMailSender javaMailSender) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.javaMailSender = javaMailSender;
    }

    @GetMapping("/")
    public String homeAction(){
        return "index";
    }




    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register2";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register2";
        }
        List<User> users = userRepository.findAll();
        boolean check = users.stream().map(o -> o.getUsername().toLowerCase()).anyMatch(o -> o.equals(user.getUsername().toLowerCase()));
        if (check) {
            model.addAttribute("registerFail", true);
            return "register2";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        Authorities authorities = new Authorities();
        authorities.setAuthority("ROLE_ADMIN");
        authorities.setUser(user);
        authoritiesRepository.save(authorities);

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setCreatedDate(new Date());
        confirmationToken.setUser(user);
        confirmationToken.setConfirmationToken(UUID.randomUUID().toString());
        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getUsername().toLowerCase());
        simpleMailMessage.setSubject("CompleteRegistration");
        simpleMailMessage.setText("To confirm your account, please click hear : "
        + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());

        javaMailSender.send(simpleMailMessage);

        return "redirect:/login";
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(Model model, @RequestParam("token") String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);

        if (confirmationToken != null) {
            User user = userRepository.findAllById(confirmationToken.getUser().getId());
            user.setEnabled(true);
            userRepository.save(user);
            return "successRegistration";
        } else {
            return "invalidRegistration";
        }

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ModelAttribute("institutions")
    public List<Institution> fetchAllInstitutions() {
        return institutionRepository.findAll();
    }

    @ModelAttribute("trashes")
    public Long sumAllBags() {
        return donationRepository.sumQuantity();
    }

    @ModelAttribute("noOfInstitutions")
    public Long sumAllInstitutions() {
        return institutionRepository.countAll();
    }


}
