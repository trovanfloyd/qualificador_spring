package br.com.qualificador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Configuracao {
	

	public static void main(String[] args) {
		SpringApplication.run(Configuracao.class, args);
	}
	
	/*@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/listavip");
		dataSource.setUsername("postgres");
		dataSource.setPassword("123456");
		
		return dataSource;
	}*/
	
}
