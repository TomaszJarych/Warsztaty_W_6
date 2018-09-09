package pl.coderslab.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.coderslab.Entity.User;
import pl.coderslab.Repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Component
public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(EmailUnique constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = userRepository.findByEmail(value);
        return Objects.isNull(user);
    }


}
