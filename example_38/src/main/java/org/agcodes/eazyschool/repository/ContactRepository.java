package org.agcodes.eazyschool.repository;

import java.util.List;
import org.agcodes.eazyschool.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

  List<Contact> findByStatus(String status);
}
