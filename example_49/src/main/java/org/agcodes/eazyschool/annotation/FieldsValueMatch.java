package org.agcodes.eazyschool.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.agcodes.eazyschool.validations.FieldsValueMatchValidator;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ ElementType.TYPE }) // Type: to use it on POJO
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {

  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

  String message() default "Fields values don't match!";

  String field();

  String fieldMatch();

  @Target({ ElementType.TYPE })
  @Retention(RetentionPolicy.RUNTIME)
  @interface List {
    FieldsValueMatch[] value();
  }
}