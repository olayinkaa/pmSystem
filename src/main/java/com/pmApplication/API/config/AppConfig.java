package com.pmApplication.API.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.mappers.ModelMapper;


@Configuration
public class AppConfig {
	
	
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

}
