package org.agcodes.eazyschool.mapper;

import org.agcodes.eazyschool.dto.ProfileDTO;
import org.agcodes.eazyschool.model.Person;
import org.mapstruct.BeanMapping;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.context.annotation.Primary;

// Makes it a Spring Bean

@Mapper(componentModel = "spring"
    , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@DecoratedWith(ProfileMapperDecorator.class)
public interface ProfileMapper {

  @Mapping(source = "address.address1", target = "address1")
  @Mapping(source = "address.address2", target = "address2")
  @Mapping(source = "address.city", target = "city")
  @Mapping(source = "address.state", target = "state")
  @Mapping(source = "address.zipCode", target = "zipCode")
  ProfileDTO toProfileDTO(Person person);

  @Mapping(target = "address", ignore = true)
  @Mapping(target = "roles", ignore = true)
  @Mapping(target = "confirmEmail", ignore = true)
  @Mapping(target = "pwd", ignore = true)
  @Mapping(target = "confirmPwd", ignore = true)
  @Mapping(target = "personId", ignore = true)
  Person toPerson(ProfileDTO dto);

  @Mapping(target = "address", ignore = true)
  @Mapping(target = "roles", ignore = true)
  @Mapping(target = "confirmEmail", ignore = true)
  @Mapping(target = "pwd", ignore = true)
  @Mapping(target = "confirmPwd", ignore = true)
  @Mapping(target = "personId", ignore = true)
  void updatePersonFromDTO(ProfileDTO profileDTO, @MappingTarget Person person);
}