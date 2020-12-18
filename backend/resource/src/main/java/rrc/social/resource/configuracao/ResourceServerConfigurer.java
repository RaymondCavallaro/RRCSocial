package rrc.social.resource.configuracao;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authz -> authz //
						// Actuator Server
				// Resource Server
				// scope config
				.antMatchers(HttpMethod.GET, "/mensagem**").hasAuthority("SCOPE_read")
				.antMatchers(HttpMethod.POST, "/mensagem").hasAuthority("SCOPE_write")
				
				// Autority Config
				.antMatchers(HttpMethod.GET, "/mensagem**").hasAnyAuthority("ROLE_CLIENT")
				.antMatchers(HttpMethod.POST, "/mensagem").hasAnyAuthority("ROLE_CLIENT")

				// Role Config
				.antMatchers(HttpMethod.GET, "/mensagem**").hasAnyRole("ROLE_USER")
				.antMatchers(HttpMethod.POST, "/mensagem").hasAnyRole("ROLE_USER")

				// Actuator Server
				.antMatchers("/actuator**").permitAll()
				.antMatchers("/actuator/**").permitAll()
				// anyRequest
				.anyRequest().authenticated()
				);
	}
}
