package com.tickets.config;

import javax.sql.DataSource;
import com.tickets.dao.TicketDAOImpl;
import com.tickets.dao.TicketDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages="com.tickets")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	//@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
//    @Bean public ReloadableResourceBundleMessageSource messageSource(){
//        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
//        reloadableResourceBundleMessageSource.setBasename("messages");
//        return reloadableResourceBundleMessageSource;
//    }
//	reloadableResourceBundleMessageSource.setBasename("/WEB-INF/messages/messages")‌​;
    @Bean public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource ResourceBundleMessageSource = new ResourceBundleMessageSource();
        ResourceBundleMessageSource.setBasenames("/WEB-INF/messages/messages","/webapp/WEB-INF/messages/messages");
        return ResourceBundleMessageSource;
    }

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/ticketsystem");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		
		return dataSource;
	}
	
	@Bean
	public TicketDAO getTicketDAO() {
		return new TicketDAOImpl(getDataSource());
	}

}
