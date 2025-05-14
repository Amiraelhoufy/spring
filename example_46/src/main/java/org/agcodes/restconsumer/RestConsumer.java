package org.agcodes.restconsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.agcodes.restconsumer.proxy")
public class RestConsumer {

	public static void main(String[] args) {
		SpringApplication.run(RestConsumer.class, args);
	}
}
