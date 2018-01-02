package ru.shaldnikita.Tags.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.vaadin.spring.events.annotation.EnableEventBus;
import ru.shaldnikita.Tags.app.security.SecurityConfig;
import ru.shaldnikita.Tags.backend.model.Reg;
import ru.shaldnikita.Tags.backend.model.User;
import ru.shaldnikita.Tags.backend.model.dict.Role;
import ru.shaldnikita.Tags.backend.repositories.RegRepository;
import ru.shaldnikita.Tags.backend.repositories.UserRepository;
import ru.shaldnikita.Tags.backend.service.UserService;
import ru.shaldnikita.Tags.web.VaadinUI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	@Autowired
	RegRepository regRepository;


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
			Long id = null;
			for(int i=1;i<20;i++){
				Reg reg = new Reg();

				List<LocalTime> time = new ArrayList<>();
				for(int j=10;j<20;j++){
					time.add(LocalTime.of(j,0));
				}

				reg.setDate(LocalDate.of(2017,12,i));
				reg.setTime(time);

				id = regRepository.save(reg).getId();
			}
			System.out.println(regRepository.findOne(id).getTime());
		};
    }


}
