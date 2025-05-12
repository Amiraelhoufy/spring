package org.agcodes.eazyschool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "class")
public class EazyClass extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "class_id")
  private Integer classId;

  @NotBlank(message = "Course name must not be blank")
  @Size(min=3, message = "Course name must be at least 3 characters long")
  private String name;


  @OneToMany(
      mappedBy = "eazyClass", // object in the other table [Use it inside the parent entity]
      fetch = FetchType.LAZY, // default
      cascade = CascadeType.PERSIST, // only persist operation
      targetEntity = Person.class
  )
  /*@RestController or @ResponseBody - You're building REST APIs and returning entities (from Jackson)*/
//  @JsonManagedReference
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private List<Person> persons;

}
