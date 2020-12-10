package social.teste.oauth2.server.mongo.conversor;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import social.teste.oauth2.server.mongo.entidade.Authorities;
import social.teste.oauth2.server.mongo.entidade.Users;

@Component
public class UserDetailsAssembler {

	@Autowired
	private GrantedAuthorityAssembler grantedAuthorityAssembler;

	public UserDetails assembleUserDetails(Users user, List<Authorities> authorities) {
		return new UserDetails() {

			@Override
			public boolean isEnabled() {
				return user.isEnabled();
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				return true;
			}

			@Override
			public boolean isAccountNonExpired() {
				return true;
			}

			@Override
			public String getUsername() {
				return user.getUsername();
			}

			@Override
			public String getPassword() {
				return user.getPassword();
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return grantedAuthorityAssembler
						.assembleGrantedAuthorities(authorities.stream().map(Authorities::getAuthority));
			}
		};
	}

}