package pl.coderslab.charity.Donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.coderslab.charity.Category.Category;
import pl.coderslab.charity.Category.CategoryRepository;
import pl.coderslab.charity.Insitution.Institution;
import pl.coderslab.charity.Insitution.InstitutionRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/donation")
@SessionAttributes("donation")
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
    public String addDonation(Model model, HttpSession session, HttpServletRequest request, SessionStatus sessionStatus) {
        model.addAttribute("donation", new Donation());
        return "formStep1";
    }

    @GetMapping("/addStep2")
    public String addDonation(@ModelAttribute Donation donation, Model model, HttpSession session, HttpServletRequest request, SessionStatus sessionStatus) {
        Donation donation1 = (Donation) session.getAttribute("donation");
        donation1.setCategoryList(donation.getCategoryList());
        model.addAttribute("donation", donation1);
        return "formStep2";
    }

    @GetMapping("/addStep3")
    public String addDonationStep2(@ModelAttribute Donation donation, HttpSession session, HttpServletRequest request, SessionStatus sessionStatus, Model model) {
        Donation donation1 = (Donation) session.getAttribute("donation");
        donation1.setQuantity(donation.getQuantity());
        model.addAttribute("donation", donation1);
        return "formStep3";
    }

    @GetMapping("/addStep4")
    public String addDonationStep3(@ModelAttribute Donation donation, HttpSession session, HttpServletRequest request, SessionStatus sessionStatus, Model model) {
        Donation donation1 = (Donation) session.getAttribute("donation");
        donation1.setInstitution(donation.getInstitution());
        model.addAttribute("donation", donation1);

        return "formStep4";
    }

    @GetMapping("/addConfirm")
    public String addDonationConfirm(@ModelAttribute Donation donation, HttpSession session, HttpServletRequest request, SessionStatus sessionStatus, Model model) {
        Donation donation1 = (Donation) session.getAttribute("donation");
        donation1.setCity(donation.getCity());
        donation1.setStreet(donation.getStreet());
        donation1.setZipCode(donation.getZipCode());
        donation1.setPickUpDate(donation.getPickUpDate());
        donation1.setPickUpTime(donation.getPickUpTime());
        donation1.setPickUpComment(donation.getPickUpComment());
        model.addAttribute("donation", donation1);

        return "formConfirm";
    }

    @PostMapping("/addConfirm")
    public String addDonationConfirm2(@ModelAttribute Donation donation, HttpSession session, HttpServletRequest request, SessionStatus sessionStatus, Model model) {
        Donation donation1 = (Donation) session.getAttribute("donation");
        donationRepository.save(donation1);
        return "form-confirmation";
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
