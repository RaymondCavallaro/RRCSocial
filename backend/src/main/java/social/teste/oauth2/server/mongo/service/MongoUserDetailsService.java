package social.teste.oauth2.server.mongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import social.teste.oauth2.server.mongo.conversor.UserDetailsAssembler;
import social.teste.oauth2.server.mongo.entidade.Authorities;
import social.teste.oauth2.server.mongo.entidade.Users;
import social.teste.oauth2.server.mongo.repository.AuthoritiesRepository;
import social.teste.oauth2.server.mongo.repository.UsersRepository;

@Component
public class MongoUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	@Autowired
	private UserDetailsAssembler assembler;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByUsername(username);
		if (user == null) {
			return null;
		}
		List<Authorities> authorities = authoritiesRepository.findByUsername(username);
		return assembler.assembleUserDetails(user, authorities);
	}
}