package org.agcodes.eazyschool.rest;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.constants.EazySchoolConstants;
import org.agcodes.eazyschool.model.Contact;
import org.agcodes.eazyschool.model.Response;
import org.agcodes.eazyschool.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@Controller
@RestController // = @Controller + @ResponseBody
@RequestMapping(path = "/api/v1/contacts")
@CrossOrigin(origins = "*")
public class ContactRestController {

  @Autowired
  private final ContactService contactService;

  public ContactRestController(ContactService contactService) {
    this.contactService = contactService;
  }

  // Accepting params in @RequestBody
  @GetMapping("/getMessagesByStatus")
//  @ResponseBody
  public List<Contact> getMessagesByStatus(@RequestParam(name="status") String status){
    return contactService.findByStatus(status);
  }

  // Accepting params in @RequestBody
  @GetMapping("/getAllMsgsByStatus")
//  @ResponseBody
  public List<Contact> getAllMsgsByStatus(@RequestBody Contact contact){
    if(contact!=null && contact.getStatus()!=null){
      return contactService.findByStatus(contact.getStatus());
    }
    return List.of();
  }

  @PostMapping("/saveMsg")
//  @ResponseBody
  public ResponseEntity<Response> saveMsg(@RequestHeader("invocationFrom") String invocationFrom,
      @Valid @RequestBody Contact contact){
    log.info("Header invocationFrom: {}", invocationFrom);
    boolean isSaved = contactService.saveMessageDetails(contact);

    Response response = new Response();
    response.setStatusCode(String.valueOf(HttpStatus.OK)); //200
    response.setStatusMsg("Message is saved successfully");
    return ResponseEntity
        .status(HttpStatus.CREATED) // 201
        .header("isMsgSaved","true") // header name & value
        .body(response); // Type in ResponseEntity<T>
  }

  @PatchMapping("/updateMsgStatus")
  public ResponseEntity<Response> updateMsgStatus(RequestEntity<Contact> requestEntity){

    Response response = new Response();
    Contact contact = requestEntity.getBody();
    Contact updatedContact = contactService.updateMsgStatus(contact.getContactId(),EazySchoolConstants.CLOSE);
    if(updatedContact ==null){
      response.setStatusCode(String.valueOf(HttpStatus.BAD_REQUEST)); // 400
      response.setStatusMsg("Invalid Contact ID received!");
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(response);
    }
    response.setStatusCode(String.valueOf(HttpStatus.OK)); // 200
    response.setStatusMsg("Message is closed successfully");
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(response);
  }

  @DeleteMapping("/deleteMsg")
  public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity){
    HttpHeaders headers = requestEntity.getHeaders();
    System.out.println(headers);
    headers.forEach((key,value)->{
      log.info("Header {} : {} ",key,value.stream().collect(Collectors.joining("|")));
    });
    Contact contact = requestEntity.getBody();
    contactService.deleteMsgById(contact.getContactId());
    Response response = new Response();
    response.setStatusCode(String.valueOf(HttpStatus.OK)); // 200
    response.setStatusMsg("Message is deleted successfully");
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(response);

  }

}
