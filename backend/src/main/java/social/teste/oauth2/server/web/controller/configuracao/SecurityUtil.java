package social.teste.oauth2.server.web.controller.configuracao;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtil {

	public static User extraiUsuario() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}