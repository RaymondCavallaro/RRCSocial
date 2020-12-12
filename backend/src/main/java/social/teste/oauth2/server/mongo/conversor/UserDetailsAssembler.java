package social.teste.oauth2.server.mongo.conversor;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import social.teste.oauth2.server.mongo.entidade.Authorities;
import social.teste.oauth2.server.mongo.entidade.Users;

@Component
public class UserDetailsAssembler {

	@Autowired
	private GrantedAuthorityAssembler grantedAuthorityAssembler;

	public UserDetails assembleUserDetails(Users user, List<Authorities> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true,
				grantedAuthorityAssembler
						.assembleGrantedAuthorities(authorities.stream().map(Authorities::getAuthority)));
	}

	public UserDetails assembleUserDetails(Map<String, Object> principal) {
		return new User((String) principal.get("username"), (String) principal.get("password"),
				(boolean) principal.get("enabled"), true, true, true, grantedAuthorityAssembler
						.assembleGrantedAuthorities((Collection<Map<String, String>>) principal.get("authorities")));
	}
}