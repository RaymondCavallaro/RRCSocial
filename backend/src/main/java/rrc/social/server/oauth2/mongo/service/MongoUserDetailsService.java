package rrc.social.server.oauth2.mongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import rrc.social.server.oauth2.mongo.conversor.UserDetailsAssembler;
import rrc.social.server.oauth2.mongo.entidade.Authorities;
import rrc.social.server.oauth2.mongo.entidade.Users;
import rrc.social.server.oauth2.mongo.repository.AuthoritiesRepository;
import rrc.social.server.oauth2.mongo.repository.UsersRepository;
import rrc.social.server.oauth2.web.controller.configuracao.SecurityUtil;

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
		Users user = usersRepository.findByUsernameAndClientId(username, SecurityUtil.extraiUsuario().getUsername());
		if (user == null) {
			return null;
		}
		List<Authorities> authorities = authoritiesRepository.findByUsername(username);
		return assembler.assembleUserDetails(user, authorities);
	}
}