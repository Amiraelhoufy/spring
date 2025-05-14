package org.agcodes.restconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
  private String statusCode;
  private String statusMsg;

}

