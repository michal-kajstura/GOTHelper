package help.got.web;

import help.got.data.TouristRepository;
import help.got.model.SignUp;
import help.got.model.Tourist;
import help.got.validators.SignUpValidator;
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
@RequestMapping("/register")
public class SignUpController {

	private TouristRepository touristRepository;
	private SignUpValidator signUpValidator;

	@Autowired
	public SignUpController(TouristRepository repo,
							SignUpValidator validator) {
		touristRepository = repo;
		signUpValidator = validator;
	}

	@GetMapping
	public String showRegistrationForm(Model model) {
		model.addAttribute("signup_model", new SignUp());
	    return "sign_up";
	}

	@PostMapping
	public String processSignUp(@Valid @ModelAttribute("signup_model") SignUp signup,
								Errors errors,
								Model model) {
		signUpValidator.validate(signup, errors);
	    if (errors.hasErrors()) {
	    	return "sign_up";
		}

	    var tourist = signToTourist(signup);
	    touristRepository.save(tourist);

	    return "redirect:/sign_in";
	}

	private Tourist signToTourist(SignUp signUp) {
		var tourist = new Tourist();
		tourist.setLogin(signUp.getLogin());
		tourist.setEmail(signUp.getEmail());
		tourist.setPassword(signUp.getPassword());
		return tourist;
	}



}