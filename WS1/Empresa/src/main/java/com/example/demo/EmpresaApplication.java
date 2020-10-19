package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
@EntityScan(basePackages  = {"com.empresa.entity"})
@SpringBootApplication(scanBasePackages = {"com.empresa"})
public class EmpresaApplication{

	public static void main(String[] args) {
		SpringApplication.run(EmpresaApplication.class, args);
	}

}
