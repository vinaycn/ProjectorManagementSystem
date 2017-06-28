package org.projector_management_system.configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class ConfigurationMVC extends WebMvcConfigurerAdapter {

	
	@Bean
	public InternalResourceViewResolver getViewResolver(){
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/jsp/");
		internalResourceViewResolver.setSuffix(".jsp");
		//internalResourceViewResolver.setViewNames("jsp/*");
		internalResourceViewResolver.setOrder(1);
		return internalResourceViewResolver;
	}
	
	
	@Bean
	public DateFormat getSimpleDateFormat(){
		return new SimpleDateFormat("MM/dd/yyyy HH:mm");
	}
}
