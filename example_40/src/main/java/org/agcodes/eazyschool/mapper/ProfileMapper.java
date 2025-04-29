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

// Makes it a Spring Bean
@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//@DecoratedWith(ProfileMapperDecorator.class)
public interface ProfileMapper {

  // Since MapStruct automatically matches fields with the same name
  //You don't have to map name, email, mobileNumber

  // Entity(Profile) →  DTO(Person)
  // Entity → DTO
  @Mapping(source = "address.address1", target = "address1")
  @Mapping(source = "address.address2", target = "address2")
  @Mapping(source = "address.city", target = "city")
  @Mapping(source = "address.state", target = "state")
  @Mapping(source = "address.zipCode", target = "zipCode")
  ProfileDTO personToProfileDTO(Person person);

  // DTO → Entity
  @Mappings({
      @Mapping(target = "address.address1", source = "address1"),
      @Mapping(target = "address.address2", source = "address2"),
      @Mapping(target = "address.city", source = "city"),
      @Mapping(target = "address.state", source = "state"),
      @Mapping(target = "address.zipCode", source = "zipCode"),
      @Mapping(target = "roles", ignore = true), // role not in DTO
      @Mapping(target = "confirmEmail", ignore = true),
      @Mapping(target = "pwd", ignore = true),
      @Mapping(target = "confirmPwd", ignore = true),
      @Mapping(target = "personId", ignore = true), // generated
      @Mapping(target = "address.addressId", ignore = true) // optional, if Address has ID
  })
  Person profileDTOtoPerson(ProfileDTO dto);


  // Mapping from ProfileDTO → existing Person (update)
   @Mappings({
      @Mapping(target = "address.address1", source = "address1"),
      @Mapping(target = "address.address2", source = "address2"),
      @Mapping(target = "address.city", source = "city"),
      @Mapping(target = "address.state", source = "state"),
      @Mapping(target = "address.zipCode", source = "zipCode"),
      @Mapping(target = "roles", ignore = true),
      @Mapping(target = "confirmEmail", ignore = true),
      @Mapping(target = "pwd", ignore = true),
      @Mapping(target = "confirmPwd", ignore = true),
      @Mapping(target = "personId", ignore = true)
  })
  void updatePersonFromProfileDTO(ProfileDTO profileDTO, @MappingTarget Person person);

}
