package pl.coderslab.charity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.Authorities.Authorities;
import pl.coderslab.charity.Authorities.AuthoritiesRepository;
import pl.coderslab.charity.Category.CategoryRepository;
import pl.coderslab.charity.Donation.DonationRepository;
import pl.coderslab.charity.Insitution.Institution;
import pl.coderslab.charity.Insitution.InstitutionRepository;
import pl.coderslab.charity.User.User;
import pl.coderslab.charity.User.UserRepository;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public String register(@ModelAttribute User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);
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
