package org.agcodes.eazyschool.service;

import org.agcodes.eazyschool.dto.ProfileDTO;
import org.agcodes.eazyschool.mapper.ProfileMapper;
import org.agcodes.eazyschool.model.Person;
import org.agcodes.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

  private final ProfileMapper profileMapper;
  private final PersonRepository personRepository;

  @Autowired
  public ProfileService(@Qualifier("profileMapperDecorator") ProfileMapper profileMapper, PersonRepository personRepository) {
    this.profileMapper = profileMapper;
    this.personRepository = personRepository;
  }
  public ProfileDTO getProfileDTOFromPerson(Person person) {

    return profileMapper.personToProfileDTO(person);

  }

  public Person updatePersonWithProfileDTO(ProfileDTO profileDTO, Person person) {

    // This call now uses the decorator behind the scenes
    profileMapper.updatePersonFromProfileDTO(profileDTO, person);
    // If using a database:
     return personRepository.save(person);

  }

}
