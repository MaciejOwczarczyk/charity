package pl.coderslab.charity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.Category.Category;
import pl.coderslab.charity.Category.CategoryRepository;

@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;

    public HomeController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String homeAction(Model model){
        Category category = new Category();
        category.setId(1L);
        category.setName("dupa");
        model.addAttribute("category", category);
        return "index";
    }
}
