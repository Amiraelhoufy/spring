package org.agcodes.eazyschool.service;

import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.constants.EazySchoolConstants;
import org.agcodes.eazyschool.model.Person;
import org.agcodes.eazyschool.model.Roles;
import org.agcodes.eazyschool.repository.PersonRepository;
import org.agcodes.eazyschool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private RolesRepository rolesRepository;

  public boolean saveNewPerson(Person person){
    boolean isSaved = false;
    Roles role = rolesRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE);
    person.setRoles(role);
    personRepository.save(person);
    if(person!=null && person.getPersonId() >0){
      isSaved = true;
    }
    return isSaved;
  }

}
