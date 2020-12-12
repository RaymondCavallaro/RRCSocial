package social.teste.oauth2.server.mongo.conversor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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

	Collection<GrantedAuthority> assembleGrantedAuthoritiesFromCollection(Collection<String> authorities) {
		return assembleGrantedAuthorities(authorities.toArray(new String[0]));
	}

	Collection<GrantedAuthority> assembleGrantedAuthorities(Map<String, String> authoritiesMap) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Map.Entry<String, String> entry : authoritiesMap.entrySet()) {
			authorities.add(assembleGrantedAuthority(entry.getValue()));
		}
		return authorities;
	}

	public Collection<GrantedAuthority> assembleGrantedAuthorities(
			Collection<Map<String, String>> collection) {
		return collection.stream().map(mapa -> assembleGrantedAuthority(mapa.get("authority")))
				.collect(Collectors.toList());
	}
}