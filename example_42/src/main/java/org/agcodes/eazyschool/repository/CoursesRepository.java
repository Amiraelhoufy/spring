package org.agcodes.eazyschool.repository;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import org.agcodes.eazyschool.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {

}
