package social.teste.resource.server.controller.configuracao;

import java.util.Optional;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class SecurityUtil {

	public static String extraiId(OidcUser principal) {
		return Optional.ofNullable(principal).map(pr -> pr.getIdToken().getTokenValue()).orElse(null);
	}

}