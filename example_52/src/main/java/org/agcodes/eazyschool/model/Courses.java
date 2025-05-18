package org.agcodes.eazyschool.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Courses extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer courseId;

  @NotBlank(message = "Name must not be blank")
  @Size(min=3, message="Name must be at least 3 characters long")
  private String name;

  @Min(value = 101, message = "Fees must be greater than 100$")
  private int fees;

  @ManyToMany(
      mappedBy = "courses",
      fetch = FetchType.EAGER,
      cascade = CascadeType.PERSIST
  )
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Person> persons = new HashSet<>();

}
