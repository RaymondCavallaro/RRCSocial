package social.teste.resource.server.configuracao;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests(authz -> authz
            .antMatchers(HttpMethod.GET, "/mensagem/**").hasAuthority("SCOPE_read") //
            .antMatchers(HttpMethod.POST, "/mensagem").hasAuthority("SCOPE_write") //
            .anyRequest().anonymous())
          .oauth2ResourceServer(oauth2 -> oauth2.jwt());
	}
}