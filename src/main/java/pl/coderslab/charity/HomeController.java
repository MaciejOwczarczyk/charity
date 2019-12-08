package pl.coderslab.charity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.Authorities.Authorities;
import pl.coderslab.charity.Authorities.AuthoritiesRepository;
import pl.coderslab.charity.Category.CategoryRepository;
import pl.coderslab.charity.Donation.DonationRepository;
import pl.coderslab.charity.Insitution.Institution;
import pl.coderslab.charity.Insitution.InstitutionRepository;
import pl.coderslab.charity.User.User;
import pl.coderslab.charity.User.UserRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;

    public HomeController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository, AuthoritiesRepository authoritiesRepository) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
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
        user.setEnabled(true);
        userRepository.save(user);
        Authorities authorities = new Authorities();
        authorities.setAuthority("ROLE_ADMIN");
        authorities.setUser(user);
        authoritiesRepository.save(authorities);
        return "redirect:/login";
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
