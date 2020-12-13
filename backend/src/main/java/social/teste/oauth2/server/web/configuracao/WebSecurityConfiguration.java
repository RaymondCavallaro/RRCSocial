package social.teste.oauth2.server.web.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import social.teste.oauth2.server.mongo.service.MongoUserDetailsService;

@Configuration
@EnableWebSecurity( debug = true )
@EnableResourceServer
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private PasswordEncoder passwordEncoder;

	@Autowired
	private MongoUserDetailsService userDetailsService;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
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
		http
//			.anonymous().and()
			.authorizeRequests(authz -> authz //

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

				// Authorization Server
				.antMatchers(HttpMethod.POST, "/oauth/token**").permitAll()
				// SSO Server
				.antMatchers("/login**").permitAll()
				.antMatchers(HttpMethod.GET, "/profile/me").authenticated()

				// anyRequest
				.anyRequest().access("hasAnyAuthority('ROLE_TRUSTED_CLIENT') && hasAnyRole('ROLE_TRUSTED_USER')")
//				.and().logout().permitAll().logoutSuccessUrl("/login")

//				.anyRequest().permitAll()
//		 .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken())
		);
	}
}
