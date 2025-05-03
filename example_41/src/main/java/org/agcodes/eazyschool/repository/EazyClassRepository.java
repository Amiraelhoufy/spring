package org.agcodes.eazyschool.repository;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.util.List;
import java.util.Optional;
import org.agcodes.eazyschool.model.EazyClass;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EazyClassRepository extends JpaRepository<EazyClass, Integer> {

  //Spring will automatically fetch persons eagerly when you call this method.
//  @EntityGraph(attributePaths = "persons")
//  Optional<EazyClass> findByClassId(Integer classId);
  
}
