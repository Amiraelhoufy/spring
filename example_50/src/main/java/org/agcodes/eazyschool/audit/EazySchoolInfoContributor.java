package org.agcodes.eazyschool.audit;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class EazySchoolInfoContributor implements InfoContributor {

  @Override
  public void contribute(Builder builder) {
    Map<String,String> eazyMap = new HashMap<>();
    eazyMap.put("App Name","EazySchool");
    eazyMap.put("App Description","EazySchool is a school management system for students and Admins.");
    eazyMap.put("App Version","1.0.0");
    eazyMap.put("Contact Email","info@eazyschool.com");
    eazyMap.put("Contact Mobile","+1(21) 673 4587");
    builder.withDetail("eazyschool-info",eazyMap);
  }
}
