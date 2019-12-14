package pl.coderslab.charity.User;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Authorities.Authorities;
import pl.coderslab.charity.Authorities.AuthoritiesRepository;
import pl.coderslab.charity.Donation.Donation;
import pl.coderslab.charity.Donation.DonationRepository;
import pl.coderslab.charity.Insitution.Institution;
import pl.coderslab.charity.Insitution.InstitutionRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;

    public AdminController(InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository, AuthoritiesRepository authoritiesRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    @GetMapping("")
    public String admin(Model model) {
        return "admin/adminIndex";
    }

    @GetMapping("/institutionShowAll")
    public String showAllInstitution(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        return "admin/showAllInstitutions";
    }

    @GetMapping("/institutionAdd")
    public String addInstitution(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/addNewInstitution";
    }

    @PostMapping("/institutionAdd")
    public String addInstitution(@ModelAttribute @Valid Institution institution, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("institution", institution);
            return "admin/addNewInstitution";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institutionShowAll";
    }

    @GetMapping("/institutionDelete/{id}")
    public String deleteInstitution(@PathVariable Long id, Model model) {
        Institution institution = institutionRepository.findAllById(id);
        List<Donation> donations = donationRepository.findAll();
        boolean check = donations.stream()
                .map(o -> o.getInstitution().getId())
                .anyMatch(o -> o.equals(institution.getId()));
        if (check) {
            model.addAttribute("failRemove", true);
            model.addAttribute("institutions", institutionRepository.findAll());
            return "admin/showAllInstitutions";
        }
        institutionRepository.delete(institution);
        return "redirect:/admin/institutionShowAll";
    }

    @GetMapping("/institutionEdit/{id}")
    public String editInstitution(@PathVariable Long id, Model model) {
        model.addAttribute("institution", institutionRepository.findAllById(id));
        return "admin/addNewInstitution";
    }

    @PostMapping("/institutionEdit/{id}")
    public String editInstitution(@PathVariable Long id, @ModelAttribute @Valid Institution institution, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/addNewInstitution";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institutionShowAll";
    }

    @GetMapping("/adminShowAll")
    public String adminShowAll(Model model) {
        List<Authorities> authorities = authoritiesRepository.findAllByAuthority("ROLE_ADMIN");
        List<User> admins = authorities.stream().map(Authorities::getUser).collect(Collectors.toList());
        model.addAttribute("admins", admins);
        return "admin/showAllAdmins";
    }

    @GetMapping("/adminAdd")
    public String adminAdd(Model model) {
        model.addAttribute("admin", new User());
        return "admin/addNewAdmin";
    }

    @PostMapping("/adminAdd")
    public String adminAdd(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("admin", user);
            return "admin/addNewAdmin";
        }
        List<User> users = userRepository.findAll();
        boolean check = users.stream().map(o -> o.getUsername().toLowerCase()).anyMatch(o -> o.equals(user.getUsername().toLowerCase()));
        if (check) {
            model.addAttribute("admin", user);
            model.addAttribute("registerFail", true);
            return "admin/addNewAdmin";
        }
        user.setPassword(encodePassword(user.getPassword()));
        userRepository.save(user);
        Authorities authorities = new Authorities();
        authorities.setAuthority("ROLE_ADMIN");
        authorities.setUser(user);
        authoritiesRepository.save(authorities);
        return "redirect:/admin/adminShowAll";
    }

    @GetMapping("/adminEdit/{id}")
    public String adminEdit(@PathVariable Long id, Model model) {
        model.addAttribute("admin", userRepository.findAllById(id));
        return "admin/addNewAdmin";
    }

    @PostMapping("/adminEdit/{id}")
    public String adminEdit(@PathVariable Long id, @ModelAttribute @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/addNewAdmin";
        }
        List<User> users = userRepository.findAll();
        boolean check = users.stream().map(o -> o.getUsername().toLowerCase()).anyMatch(o -> o.equals(user.getUsername().toLowerCase()));
        if (check) {
            model.addAttribute("admin", user);
            model.addAttribute("registerFail", true);
            return "admin/addNewAdmin";
        }
        List<Authorities> authorities = authoritiesRepository.findAll();
        for (Authorities authority : authorities) {
            if (authority.getUser().getId().equals(user.getId())) {
                authoritiesRepository.delete(authority);
            }
        }
        user.setPassword(encodePassword(user.getPassword()));
        userRepository.save(user);
        Authorities authorities1 = new Authorities();
        authorities1.setAuthority("ROLE_ADMIN");
        authorities1.setUser(user);
        authoritiesRepository.save(authorities1);

        return "redirect:/admin/adminShowAll";
    }

    @GetMapping("/adminDelete/{id}")
    public String deleteAdmin(@PathVariable Long id, @AuthenticationPrincipal UserDetails customUser, Model model) {
        User user = userRepository.findAllById(id);
        if (customUser.getUsername().equals(user.getUsername())) {
            model.addAttribute("failRemove", true);
            List<Authorities> authorities = authoritiesRepository.findAllByAuthority("ROLE_ADMIN");
            List<User> admins = authorities.stream().map(Authorities::getUser).collect(Collectors.toList());
            model.addAttribute("admins", admins);
            return "admin/showAllAdmins";
        }
        List<Authorities> authorities = authoritiesRepository.findAll();
        for (Authorities authority : authorities) {
            if (authority.getUser().getId().equals(user.getId())) {
                authoritiesRepository.delete(authority);
            }
        }
        userRepository.delete(user);
        return "redirect:/admin/adminShowAll";

    }

    private String encodePassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

}
