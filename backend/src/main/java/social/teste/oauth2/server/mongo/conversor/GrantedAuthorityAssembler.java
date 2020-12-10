package social.teste.oauth2.server.mongo.conversor;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class GrantedAuthorityAssembler {

	public GrantedAuthority assembleGrantedAuthority(String authorities) {
		return new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return authorities;
			}
		};
	}

	Collection<GrantedAuthority> assembleGrantedAuthorities(Stream<String> authorities) {
		return authorities.map(this::assembleGrantedAuthority).collect(Collectors.toList());
	}

	Collection<GrantedAuthority> assembleGrantedAuthorities(String... authorities) {
		return assembleGrantedAuthorities(Stream.of(authorities));
	}

	Collection<GrantedAuthority> assembleGrantedAuthorities(Collection<String> authorities) {
		return assembleGrantedAuthorities(authorities.toArray(new String[0]));
	}
}