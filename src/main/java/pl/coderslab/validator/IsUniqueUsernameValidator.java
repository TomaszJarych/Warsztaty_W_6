package pl.coderslab.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.coderslab.Repository.UserRepository;
import pl.coderslab.entity.User;

public class IsUniqueUsernameValidator implements ConstraintValidator<IsUniqueUsername, String> {

  @Autowired 
  private UserRepository repository;

  @Override
  public void initialize(IsUniqueUsername constraintAnnotation) {}

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    User user = repository.findByUsername(value);
    return Objects.isNull(user);
  }
}
