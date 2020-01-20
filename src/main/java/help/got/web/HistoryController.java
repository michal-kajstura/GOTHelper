package help.got.web;


import help.got.model.History;
import help.got.model.SignIn;
import help.got.validators.HistoryValidator;
import help.got.validators.SignInValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/list")
public class HistoryController {
    private HistoryValidator validator;

    @Autowired
    public HistoryController(HistoryValidator val) {
        validator = val;
    }

    @GetMapping
    public String showHistory(Model model) {
        model.addAttribute("history_model", new History());
        return "history";
    }

}
