package org.agcodes.eazyschool.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.dto.ProfileDTO;
import org.agcodes.eazyschool.mapper.ProfileMapper;
import org.agcodes.eazyschool.model.Person;
import org.agcodes.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class ProfileService {

  private final ProfileMapper profileMapper;
  private final PersonRepository personRepository;
  @Autowired
  public ProfileService(ProfileMapper profileMapper, PersonRepository personRepository) {
    this.profileMapper = profileMapper;
    this.personRepository = personRepository;
  }
  public ProfileDTO getProfileDTOFromPerson(Person person) {

    return profileMapper.toProfileDTO(person);

  }
  @Transactional
  public Person updatePersonWithProfileDTO(ProfileDTO profileDTO, Person person) {

    // This call now uses the decorator behind the scenes
    profileMapper.updatePersonFromDTO(profileDTO, person);
    // If using a database:
    Person updatedPerson = personRepository.save(person);
    return updatedPerson;

  }
}
