package help.got.web;

import help.got.model.Details;
import help.got.validators.DetailsValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/details")
public class DetailsController {
    private DetailsValidator validator;

    @Autowired
    public DetailsController(DetailsValidator val) {
        validator = val;
    }

    @GetMapping
    public String showDetails(Model model) {
        model.addAttribute("details_model", new Details());
        return "details";
    }

}
