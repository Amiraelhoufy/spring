package org.agcodes.eazyschool.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/* @Data: Provided by lombok library which generates getters & setters,
  equals(), hashcode(), toString() methods & constructors at compile time
  This makes our code short and clean
  */
@Data
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(
      name = "contact_id",
      updatable = false
  )
  private int contactId;

  @NotBlank(message = "Name must not be blank")
  @Size(min=3, message="Name must be at least 3 characters long")
  private String name;

  @NotBlank(message = "Mobile number must not be blank")
  @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits")
  private String mobileNum;

  @NotBlank(message = "Email must not be blank")
  @Email(message = "Please provide a valid email address")
  private String email;

  @NotBlank(message = "Subject must not be blank")
  @Size(min=5, message="Subject must be at least 5 characters long")
  private String subject;

  @NotBlank(message = "Message must not be blank")
  @Size(min=10, message = "Message must be at least 10 characters long")
  private String message;

  private String status;

}
