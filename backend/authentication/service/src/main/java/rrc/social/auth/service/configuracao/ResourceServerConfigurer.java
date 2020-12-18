package rrc.social.auth.service.configuracao;

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
						.antMatchers("/actuator**").permitAll()
						.antMatchers("/actuator/**").permitAll()
						// Authorization Server
						.antMatchers(HttpMethod.POST, "/oauth/token**").permitAll()
						// SSO Server
						.antMatchers(HttpMethod.GET, "/profile/me").authenticated()
						// anyRequest
						.anyRequest().authenticated()
				);
	}
}
