package com.projectmanagement.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ProjectManagementApplication.class);
		app.run(args);
	}
}
