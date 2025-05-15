package org.agcodes.eazyschool.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component("eazySchoolProperties")
@Data
@ConfigurationProperties(prefix = "eazyschool")
//@PropertySource(value = "classpath:application.properties")
@Validated
public class EazySchoolProperties {

  @Min(value = 5, message = "must be between 5 and 25")
  @Max(value = 25, message = "must be between 5 and 25")
  private int pageSize;

  private Map<String,String> contact;
  private List<String> branches;

}
