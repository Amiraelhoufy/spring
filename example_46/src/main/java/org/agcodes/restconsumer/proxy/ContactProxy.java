package org.agcodes.restconsumer.proxy;

import feign.Headers;
import java.util.List;
import org.agcodes.restconsumer.config.ProjectConfiguration;
import org.agcodes.restconsumer.model.Contact;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "contact", url="http://localhost:8080/api/v1/contacts",
configuration = ProjectConfiguration.class)
public interface ContactProxy {

  @RequestMapping(method = RequestMethod.GET, value = "/getMessagesByStatus")
  @Headers(value = "Content-Type: application/json")
  public List<Contact> getMessagesByStatus(@RequestParam(name="status") String status);

}
