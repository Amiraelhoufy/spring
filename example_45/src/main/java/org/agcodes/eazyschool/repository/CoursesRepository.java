package org.agcodes.eazyschool.repository;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.util.List;
import org.agcodes.eazyschool.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
  /*
  * JPA allows us to apply static sorting by using
  * "OrderBy" keyword
  * Property name
  * Direction
  * */
  List<Courses> findByOrderByNameDesc();

// Default Sorting is Asc - Optional to use
  List<Courses> findByOrderByName();
}
