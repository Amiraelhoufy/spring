package org.agcodes.eazyschool.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.agcodes.eazyschool.annotation.FieldsValueMatch;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator
    implements ConstraintValidator<FieldsValueMatch, Object> {
  // Annotation, Object(POJO)

  private String field;
  private String fieldMatch;

  @Override
  public void initialize(FieldsValueMatch constraintAnnotation) {
    this.field = constraintAnnotation.field();
    this.fieldMatch = constraintAnnotation.fieldMatch();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    Object fieldValue = new BeanWrapperImpl(value)
        .getPropertyValue(field);
    Object fieldMatchValue = new BeanWrapperImpl(value)
        .getPropertyValue(fieldMatch);
    /*
    If both fields are null, the form is considered valid.
    If one field is null and the other is not, the form is invalid.
    */
    if (fieldValue != null) {
      // to skip the password compare by JPA Framework
      // as it'll compare a hashed password with raw confirmed password which will fail
//      if(fieldValue.toString().startsWith("$2a")){
//        return true;
//      }
      return fieldValue.equals(fieldMatchValue);
    } else {
      return fieldMatchValue == null;
    }
  }
}