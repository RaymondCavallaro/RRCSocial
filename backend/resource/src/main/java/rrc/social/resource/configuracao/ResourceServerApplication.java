package rrc.social.resource.configuracao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "rrc.social.resource" })
public class ResourceServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ResourceServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerApplication.class, args);
	}
}
