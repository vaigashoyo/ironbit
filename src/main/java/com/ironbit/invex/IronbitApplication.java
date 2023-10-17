package com.ironbit.invex;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Empleado API",version = "1", description = "Services CRUD Empleado"))
public class IronbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(IronbitApplication.class, args);
	}

}
