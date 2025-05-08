package org.agcodes.eazyschool.repository;

import java.util.List;
import org.agcodes.eazyschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

  List<Contact> findByStatus(String status);
  // Dynamic Pagination
  Page<Contact> findByStatus(String status, Pageable pageable);
}
