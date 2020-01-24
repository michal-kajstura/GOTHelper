package help.got.validators;

import help.got.data.TripRepository;
import help.got.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripValidator {
    public class Error {
        private String field;
        private String message;
        public Error(String tfield, String tmessage) {
            this.field = tfield;
            this.message = tmessage;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }
    }

    private TripRepository tripRepository;

    @Autowired
    public TripValidator(TripRepository repo) {
        tripRepository = repo;
    }

    public List<Error> validate(Trip trip) {

        var errors = new ArrayList<Error>();
        if (trip.getPath().size() == 0) {
            errors.add(new Error("path", "Ścieżka jest pusta!"));
        }

        var empty = trip.getName().isEmpty();
        if (empty) {
            errors.add(new Error("name", "Nazwa nie może być pusta"));
        }

        if (!empty) {
            var trips = tripRepository.findByName(trip.getName());
            if (trips.size() != 0) {
                errors.add(new Error("name", "Wycieczka o podanej nazwie istnieje"));
            }
        }
        return errors;
    }
}
