package rrc.social.server.resource.configuracao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "rrc.social.server.resource", "rrc.social.server.oauth2.mongo", "rrc.social.server.oauth2.web" })
public class BackendApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BackendApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
