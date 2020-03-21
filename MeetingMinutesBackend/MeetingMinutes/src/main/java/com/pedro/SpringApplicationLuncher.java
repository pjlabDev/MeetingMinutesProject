/**
 * 
 */
package com.pedro;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 * @author pedro
 *
 */
@SpringBootApplication
public class SpringApplicationLuncher extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringApplicationLuncher.class, args);
	}

}
