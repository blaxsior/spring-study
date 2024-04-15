package com.study.blaxsior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class BlaxsiorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlaxsiorApplication.class, args);
	}

}
