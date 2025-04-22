package org.agcodes.eazyschool.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.constants.EazySchoolConstants;
import org.agcodes.eazyschool.controller.ContactController;
import org.agcodes.eazyschool.model.Contact;
import org.agcodes.eazyschool.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@Service
@ApplicationScope
public class ContactService {

  @Autowired
  private ContactRepository contactRepository;
  public ContactService() {
    System.out.println("ContactService bean is initialized");
  }

  public boolean saveMessageDetails(Contact contact){
    boolean isSaved= false;
    contact.setStatus(EazySchoolConstants.OPEN);
    contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
    contact.setCreatedAt(LocalDateTime.now());
    Contact savedContact = contactRepository.save(contact);
    if(savedContact !=null && savedContact.getContactId()>0){
      isSaved=true;
    }
    return isSaved;
  }

  public List<Contact> findMsgsWithOpenStatus() {
    List<Contact> contactMsgs = contactRepository.findByStatus(EazySchoolConstants.OPEN);
    return contactMsgs;
  }

  public Boolean updateMsgStatus(int contactId, String updatedBy) {
    boolean isUpdated = false;

    Optional<Contact> contact = contactRepository.findById(contactId);
    contact.ifPresent(contact1 -> {
          contact1.setStatus(EazySchoolConstants.CLOSE);
          contact1.setUpdatedBy(updatedBy);
          contact1.setUpdatedAt(LocalDateTime.now());
    });

    Contact updatedContact = contactRepository.save(contact.get());
    if(updatedContact!=null && updatedContact.getUpdatedBy()!=null){
      isUpdated=true;
    }

    return isUpdated;
  }

}