package org.agcodes.eazyschool.service;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.constants.EazySchoolConstants;
import org.agcodes.eazyschool.model.Contact;
import org.agcodes.eazyschool.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

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

  public Boolean updateMsgStatus(int contactId) {
    boolean isUpdated = false;

    Optional<Contact> contact = contactRepository.findById(contactId);
    contact.ifPresent(contact1 -> {
          contact1.setStatus(EazySchoolConstants.CLOSE);
    });

    Contact updatedContact = contactRepository.save(contact.get());
    if(updatedContact!=null && updatedContact.getUpdatedBy()!=null){
      isUpdated=true;
    }

    return isUpdated;
  }

}