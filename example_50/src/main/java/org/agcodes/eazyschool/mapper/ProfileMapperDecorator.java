package org.agcodes.eazyschool.mapper;

import org.agcodes.eazyschool.dto.ProfileDTO;
import org.agcodes.eazyschool.model.Address;
import org.agcodes.eazyschool.model.Person;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Component("profileMapperDecorator")
@Primary
public abstract class ProfileMapperDecorator implements ProfileMapper {
  protected ProfileMapper delegate;

  @Autowired
  public void setDelegate(ProfileMapper delegate) {
    this.delegate = delegate;
  }
  @Override
  public ProfileDTO toProfileDTO(Person person) {
    ProfileDTO dto = delegate.toProfileDTO(person);
    // Add any decoration logic here if needed
    return dto;
  }

  @Override
  public Person toPerson(ProfileDTO dto) {
    Person person = delegate.toPerson(dto);
    if (dto != null) {
      person.setAddress(createOrUpdateAddress(null, dto));
    }
    return person;
  }

  @Override
  public void updatePersonFromDTO(ProfileDTO dto, Person person) {
    if (dto == null) {
      return;
    }

    delegate.updatePersonFromDTO(dto, person);
    person.setAddress(createOrUpdateAddress(person.getAddress(), dto));
  }

  private Address createOrUpdateAddress(Address existing, ProfileDTO dto) {
    Address address = existing != null ? existing : new Address();

    if (dto.getAddress1() != null) address.setAddress1(dto.getAddress1());
    if (dto.getAddress2() != null) address.setAddress2(dto.getAddress2());
    if (dto.getCity() != null) address.setCity(dto.getCity());
    if (dto.getState() != null) address.setState(dto.getState());
    if (dto.getZipCode() != null) address.setZipCode(dto.getZipCode());

    return address;
  }
}