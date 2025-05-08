package org.agcodes.eazyschool.service;


import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.constants.EazySchoolConstants;
import org.agcodes.eazyschool.model.Contact;
import org.agcodes.eazyschool.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

  public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
    int pageSize = 5;
    Pageable pageable = (Pageable) PageRequest.of(pageNum-1, pageSize,
        sortDir.equals("asc")? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
    Page<Contact> contactMsgs = contactRepository.findByStatus(EazySchoolConstants.OPEN,pageable);
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