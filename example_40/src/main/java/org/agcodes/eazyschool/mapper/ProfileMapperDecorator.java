package org.agcodes.eazyschool.mapper;

import org.agcodes.eazyschool.dto.ProfileDTO;
import org.agcodes.eazyschool.model.Address;
import org.agcodes.eazyschool.model.Person;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component("profileMapperDecorator")
public class ProfileMapperDecorator implements ProfileMapper {
  private final ProfileMapper delegate;

  @Autowired
  public ProfileMapperDecorator(@Qualifier("profileMapperImpl") ProfileMapper delegate) {
    this.delegate = delegate;
  }
  @Mapping(source = "address.state", target = "state")
  @Override
  public ProfileDTO personToProfileDTO(Person person) {
    return delegate.personToProfileDTO(person);
  }

  @Override
  public Person profileDTOtoPerson(ProfileDTO dto) {
    return delegate.profileDTOtoPerson(dto);
  }

 @Override
  public void updatePersonFromProfileDTO(ProfileDTO profileDTO, Person person) {

   System.out.println("test person" + person);
   System.out.println("test profileDTO" + profileDTO);
    // Ensure address is initialized
    if (person.getAddress() == null) {
      person.setAddress(new Address());
    }
    // Now delegate the update logic to MapStruct
    delegate.updatePersonFromProfileDTO(profileDTO, person);
  }
}
