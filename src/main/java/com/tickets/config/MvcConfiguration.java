package com.tickets.config;

import javax.sql.DataSource;

import com.tickets.dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Map;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.tickets")
@Import({ WebSecurityConfig.class })
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	//@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

//	reloadableResourceBundleMessageSource.setBasename("/WEB-INF/messages/messages")‌​;
    @Bean public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource ResourceBundleMessageSource = new ResourceBundleMessageSource();
        ResourceBundleMessageSource.setBasename("messages");
        return ResourceBundleMessageSource;
    }

	@Bean(name = "dataSource")
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

	@Bean
	public UserDAO getUserDAO() {
		return new UserDAOImpl(getDataSource());
	}

	@Bean
	public ChartsDAO getchartsDAO() {
		return new ChartsDAOImpl();
	}

//	@Bean
//	public DefaultServletHttpRequestHandler defaultServletHttpRequestHandler() {
//		return new DefaultServletHttpRequestHandler();

	//default server map
//	@Bean
//	public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
//		Map<String, String> urlMap = new ManagedMap<String, String>();
//		urlMap.put("/**", "");
//
//		SimpleUrlHandlerMapping hm = new SimpleUrlHandlerMapping();
//		hm.setUrlMap(urlMap);
//		return hm;
//	}

}
