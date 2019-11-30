package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.Category.CategoryRepository;
import pl.coderslab.charity.Donation.DonationRepository;
import pl.coderslab.charity.Insitution.Institution;
import pl.coderslab.charity.Insitution.InstitutionRepository;

import java.util.List;

@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public HomeController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @GetMapping("/")
    public String homeAction(){
        return "index";
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
