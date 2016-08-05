package fr.aoufi.springsamplexml.conf;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import fr.aoufi.springsamplexml.dao.PersonneDAO;
import fr.aoufi.springsamplexml.dao.PersonneJDBCDAO;
import fr.aoufi.springsamplexml.dao.PersonneJPADAO;
import fr.aoufi.springsamplexml.service.PersonneService;
import fr.aoufi.springsamplexml.service.PersonneServiceImpl;

@Configuration
@PropertySource(value="classpath:/config.properties")
public class ApplicationConfiguration {
	
	@Autowired
	private Environment environment;
	
	@Value("${db.driverclassname}")
	private String driverClassName;
	
	@Value("${db.url}")
	private String url;
	
	@Value("${db.username}")
	private String username;
	
	@Value("${db.password}")
	private String password;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesplaceHolderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean    
	public DataSource dataSource() {
		
	System.out.println(" VALEUR driverclassname avec @value : "+driverClassName);
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("db.driverClass"));
		dataSource.setUrl(environment.getProperty("db.url"));
		dataSource.setUsername(environment.getProperty("db.username"));
		dataSource.setPassword(environment.getProperty("db.password"));
		
		return dataSource;
	}
	
	@Bean
	public PersonneService PersonneService(PersonneDAO personneDAO){
		PersonneServiceImpl personneService = new PersonneServiceImpl();
		personneService.setPersonneDAO(personneDAO);
		return personneService;
	}
	
	@Bean(name="personneDAO")
	@Profile("jpa")
	public PersonneDAO personneJPADAO(){
		return new PersonneJPADAO();
	}
	
	@Bean(name="personneDAO")
	@Profile("jdbc")
	public PersonneDAO personneJDBCDAO(){
		return new PersonneJDBCDAO();
	}

}
