package help.got.validators;

import help.got.data.TourRepository;
import help.got.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class DetailsValidator implements Validator {

    private TourRepository tourRepository;

    @Autowired
    public DetailsValidator(TourRepository repo) {
        tourRepository = repo;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Tour.class.equals(aClass);
    }

    public void validate(Object object, Errors e) {
        var tour = tourRepository.findTourByIdT((long) 0);

        if (tour == null) {
            e.rejectValue("idT", "no.such.tour");
            return;
        }
    }
}
