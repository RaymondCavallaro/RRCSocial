package rrc.social.auth.mongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import rrc.social.auth.common.controller.configuracao.SecuritySingleton;
import rrc.social.auth.mongo.conversor.UserDetailsAssembler;
import rrc.social.auth.mongo.entidade.Authorities;
import rrc.social.auth.mongo.entidade.Users;
import rrc.social.auth.mongo.repository.AuthoritiesRepository;
import rrc.social.auth.mongo.repository.UsersRepository;

@Profile("mongo")
@Component("users")
public class MongoUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	@Autowired
	private UserDetailsAssembler assembler;
	@Autowired
	private SecuritySingleton securityUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByUsernameAndClientId(username, securityUtil.extraiUsuario().getUsername());
		if (user == null) {
			return null;
		}
		List<Authorities> authorities = authoritiesRepository.findByUsername(username);
		return assembler.assembleUserDetails(user, authorities);
	}
}