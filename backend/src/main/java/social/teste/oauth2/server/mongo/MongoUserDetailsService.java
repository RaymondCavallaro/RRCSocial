package social.teste.oauth2.server.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import social.teste.resource.server.repository.MensagemRepository;

@Component
public class MongoUserDetailsService implements UserDetailsService {

	@Autowired
	private MensagemRepository mensagemRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
}