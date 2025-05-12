package org.agcodes.eazyschool.service;


import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.constants.EazySchoolConstants;
import org.agcodes.eazyschool.model.Contact;
import org.agcodes.eazyschool.model.Person;
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

  public boolean saveMessageDetails(Contact contact) {
    boolean isSaved = false;
    contact.setStatus(EazySchoolConstants.OPEN);
    Contact savedContact = contactRepository.save(contact);
    if (savedContact != null && savedContact.getContactId() > 0) {
      isSaved = true;
    }
    return isSaved;
  }

  public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
    int pageSize = 5;
    Pageable pageable = (Pageable) PageRequest.of(pageNum - 1, pageSize,
        sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
    /* ========== Derived Query Method ========= */
    Page<Contact> contactMsgs = contactRepository.findByStatus(EazySchoolConstants.OPEN, pageable);

    /* ========== Testing @NameQuery ========= */
    //    Page<Contact> contactMsgs = contactRepository.findOpenMsgs(EazySchoolConstants.OPEN,pageable);

    /* ========== Testing @NameNativeQuery ========= */
    //    Page<Contact> contactMsgs = contactRepository.findOpenMsgsNative(EazySchoolConstants.OPEN,pageable);

    return contactMsgs;
  }

  public Boolean updateMsgStatus(int contactId) {
    /* ========== Derived Query Method ========= */
    int updatedRows = contactRepository.updateStatusById(contactId, EazySchoolConstants.CLOSE);

    /* ========== Testing @NameQuery ========= */
    //    int updatedRows = contactRepository.updateMsgStatus(contactId, EazySchoolConstants.CLOSE);

    /* ========== Testing @NameNativeQuery ========= */
    //    int updatedRows = contactRepository.updateMsgStatusNative(contactId, EazySchoolConstants.CLOSE);

    return updatedRows > 0 ? true : false;

  /*
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

    */

  }

  public List<Contact> findByStatus(String status) {
    return contactRepository.findByStatus(status);
  }

  public void deleteMsgById(int contactId) {
    contactRepository.deleteById(contactId);
  }

  public Contact updateMsgStatus(int id,String status){
    Optional<Contact> contactOptional = contactRepository.findById(id);
    if(contactOptional.isPresent()){
      Contact updatedContact = contactOptional.get();
      updatedContact.setStatus(status);
      contactRepository.save(updatedContact);
      return updatedContact;
    }
    return null;
  }
}