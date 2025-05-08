package org.agcodes.eazyschool.validations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import org.agcodes.eazyschool.annotation.PasswordValidator;

public class PasswordStrengthValidator implements
    ConstraintValidator<PasswordValidator, String> {

  List<String> weakPasswords;

  @Override
  public void initialize(PasswordValidator passwordValidator) {
    weakPasswords = Arrays.asList("12345", "password", "qwerty");
  }

  @Override
  public boolean isValid(String passwordField, // end-user input html page
      ConstraintValidatorContext cxt) {
    return passwordField != null && (!weakPasswords.contains(passwordField));
  }
}