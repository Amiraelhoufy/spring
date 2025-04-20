package org.agcodes.eazyschool.service;

import java.time.LocalDateTime;
import java.util.List;
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
//  private static Logger log = LoggerFactory.getLogger(ContactService.class);

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
    int result = contactRepository.saveContactMsg(contact);
    if(result>0){
      isSaved=true;
    }
    return isSaved;
  }

  public List<Contact> findMsgsWithOpenStatus() {
    List<Contact> contactMsgs = contactRepository.findMsgsWithOpenStatus(EazySchoolConstants.OPEN);
    return contactMsgs;
  }

  public Boolean updateMsgStatus(int contactId, String updatedBy) {
    boolean isUpdated = false;

    int result = contactRepository.updateMsgStatus(contactId,EazySchoolConstants.CLOSE, updatedBy);
    if(result>0){
      isUpdated=true;
    }

    return isUpdated;
  }

}