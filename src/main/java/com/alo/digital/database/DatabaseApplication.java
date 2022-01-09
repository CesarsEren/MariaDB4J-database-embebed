package com.alo.digital.database;

import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import com.alo.digital.database.mariadb.CustomerMariaDB4SpringService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@SpringBootApplication
@EnableScheduling
public class DatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}

	@Bean
	public MariaDB4jSpringService mariaDB4jSpringService() {
		return new CustomerMariaDB4SpringService();
	}

}
