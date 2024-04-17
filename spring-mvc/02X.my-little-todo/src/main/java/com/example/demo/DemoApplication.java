package com.example.demo;

import com.example.demo.todo.repo.TodoNoteRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;

@EnableJpaAuditing
@ServletComponentScan
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		Arrays.stream(ctx.getBeanDefinitionNames()).forEach(name -> System.out.println("name = " + name));
//		var repo = ApplicationContextProvider.getBean(TodoNoteRepository.class);
//		System.out.println("repo = " + repo);
	}
}