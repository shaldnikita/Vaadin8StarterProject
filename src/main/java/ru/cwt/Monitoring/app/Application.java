package ru.cwt.Monitoring.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.vaadin.spring.events.annotation.EnableEventBus;
import ru.cwt.Monitoring.app.security.SecurityConfig;
import ru.cwt.Monitoring.backend.model.User;
import ru.cwt.Monitoring.backend.model.dict.Role;
import ru.cwt.Monitoring.backend.repositories.UserRepository;
import ru.cwt.Monitoring.web.VaadinUI;
import ru.cwt.Monitoring.backend.service.UserService;

/**
 * @author n.shaldenkov on 23.11.2017
 */

@SpringBootApplication(scanBasePackageClasses = { VaadinUI.class, Application.class,
        SecurityConfig.class,
        UserService.class})
@EntityScan(basePackageClasses = User.class)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EnableEventBus
public class Application extends SpringBootServletInitializer {

	public static final String APP_URL = "/";
	public static final String LOGIN_URL = "/login.html";
	public static final String LOGOUT_URL = "/login.html?logout";
	public static final String LOGIN_FAILURE_URL = "/login.html?error";
	public static final String LOGIN_PROCESSING_URL = "/login";

	@Autowired
    UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Bean
    public ApplicationRunner applicationRunner(){
	    return (args) ->{

			User user = new User();
			user.setPassword("123");
			user.setRole(Role.ADMIN);
			user.setLogin("admin");
			user.setEmail("admin@admin.ru");

			userService.saveNew(user);
		};
    }


}
