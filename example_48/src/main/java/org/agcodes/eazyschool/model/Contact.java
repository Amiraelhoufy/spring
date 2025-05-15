package org.agcodes.eazyschool.model;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.SqlResultSetMappings;
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
@NamedQueries(
    {
        @NamedQuery(name = "Contact.findOpenMsgs",
        query = "SELECT c FROM Contact c WHERE c.status = :status"),
        @NamedQuery(name = "Contact.updateMsgStatus",
        query = "UPDATE Contact c SET c.status=?2 WHERE c.contactId = ?1")
    }
)
@SqlResultSetMappings({
 @SqlResultSetMapping(name = "SqlResultSetMapping.count",columns = @ColumnResult(name="cnt"))
})
@NamedNativeQueries(
    {
        @NamedNativeQuery(name = "Contact.findOpenMsgsNative",
            query = "SELECT * FROM contact_msg WHERE status = :status"
            ,resultClass = Contact.class),
        // You'll have to provide the query for pagination to understand the no of records available
        // Dynamic Sorting won't work
        @NamedNativeQuery(name = "Contact.findOpenMsgsNative.count",
            query = "SELECT count(*) as cnt FROM contact_msg c WHERE c.status = :status"
            ,resultSetMapping = "SqlResultSetMapping.count"),
        /*
        Spring Data JPA doesn't support dynamic sorting for native queries.
        As the doint that will require Spring Data JPA to analyze the provided statment and generate Order By clause in database statement.
        Which is very complex and not currently supported by Spring Data JPA
        * */
        @NamedNativeQuery(name = "Contact.updateMsgStatusNative",
            query = "UPDATE contact_msg SET status=?2 WHERE contact_id = ?1")
    }
)
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
