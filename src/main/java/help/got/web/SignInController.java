package help.got.web;

import help.got.model.SignIn;
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
@RequestMapping("/login")
public class SignInController {

	private SignInValidator validator;

	@Autowired
	public SignInController(SignInValidator val) {
		validator = val;
	}

	@GetMapping
	public String showLoginForm(Model model) {
	    model.addAttribute("signin_model", new SignIn());
	    return "sign_in";
	}

	@PostMapping
	public String processSignIn(@Valid @ModelAttribute("signin_model") SignIn signIn,
								Errors errors,
								Model model) {
		validator.validate(signIn, errors);

	    if (errors.hasErrors()) {
	    	return "sign_in";
		}

	    return "redirect:/sign_up";
	}


}
