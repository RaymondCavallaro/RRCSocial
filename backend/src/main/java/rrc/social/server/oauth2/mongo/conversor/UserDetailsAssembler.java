package rrc.social.server.oauth2.mongo.conversor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import rrc.social.server.oauth2.mongo.entidade.Authorities;
import rrc.social.server.oauth2.mongo.entidade.Users;

@Component
public class UserDetailsAssembler {

	@Autowired
	private GrantedAuthorityAssembler grantedAuthorityAssembler;

	public UserDetails assembleUserDetails(Users user, Collection<Authorities> authorities) {
		return assembleUserDetailsFromGrantedAuthority(user, grantedAuthorityAssembler
				.assembleGrantedAuthorities(authorities.stream().map(Authorities::getAuthority)));
	}

	public UserDetails assembleUserDetailsFromGrantedAuthority(Users user, Collection<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}
}