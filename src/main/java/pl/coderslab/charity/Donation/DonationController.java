package pl.coderslab.charity.Donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Category.Category;
import pl.coderslab.charity.Category.CategoryRepository;
import pl.coderslab.charity.Insitution.Institution;
import pl.coderslab.charity.Insitution.InstitutionRepository;

import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;

    public DonationController(DonationRepository donationRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }


    @GetMapping("/add")
    public String addDonation(Model model) {
        model.addAttribute("donation", new Donation());
        return "form";
    }

    @PostMapping("/add")
    public String addDonation(@ModelAttribute Donation donation, @RequestParam List<Category> categories) {
        donation.setCategoryList(categories);
        donationRepository.save(donation);
        return "redirect:/";
    }

    @ModelAttribute("categories")
    public List<Category> fetchAllCategories() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> fetchAllInstitutions() {
        return institutionRepository.findAll();
    }

}
