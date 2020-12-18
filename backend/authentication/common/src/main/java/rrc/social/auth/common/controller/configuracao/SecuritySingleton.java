package rrc.social.auth.common.controller.configuracao;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class SecuritySingleton {

	public User extraiUsuario() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}