package rrc.social.auth.service.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableWebSecurity( debug = true )
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService users;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(users).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		if (passwordEncoder == null) {
			passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		}
		return passwordEncoder;
	}

    @Override
	protected void configure(HttpSecurity http) throws Exception {
//		http
////			.anonymous().and()
//			.authorizeRequests(authz -> authz //
//				// Actuator Server
//				.antMatchers(HttpMethod.GET, "/actuator/health**").permitAll()
//				.antMatchers(HttpMethod.GET, "/actuator/info**").permitAll()
//				// Authorization Server
//				.antMatchers(HttpMethod.POST, "/oauth/token**").permitAll()
//				// SSO Server
//				.antMatchers(HttpMethod.GET, "/profile/me").authenticated()
//
//				// anyRequest
//				.anyRequest().authenticated()
////				.anyRequest().permitAll()
////		 .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken())
//		);
	}
}
