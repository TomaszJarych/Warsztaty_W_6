package pl.coderslab.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.coderslab.Repository.UserRepository;
import pl.coderslab.entity.User;

public class IsUniqueEmailValidator implements ConstraintValidator<IsUniqueEmail, String> {

  @Autowired private UserRepository repository;

  @Override
  public void initialize(IsUniqueEmail constraintAnnotation) {}

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    User user = repository.findByEmail(value);
    return Objects.isNull(user);
  }
}
