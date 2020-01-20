package help.got.validators;

import help.got.data.TourRepository;
import help.got.model.History;
import help.got.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class HistoryValidator implements Validator {

    private TourRepository tourRepository;

    @Autowired
    public HistoryValidator(TourRepository repo) {
        tourRepository = repo;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Tour.class.equals(aClass);
    }

    public void validate(Object object, Errors e) {
//        var history = (History) object;
        var tours = tourRepository.findAll();

        if (tours == null) {
            e.rejectValue("tourList","no.tours");
            return;
        }

    }
}
