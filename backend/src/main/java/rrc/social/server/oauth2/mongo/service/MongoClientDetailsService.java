package rrc.social.server.oauth2.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import rrc.social.server.oauth2.mongo.conversor.ClientDetailsAssembler;
import rrc.social.server.oauth2.mongo.entidade.OauthClientDetails;
import rrc.social.server.oauth2.mongo.repository.OauthClientDetailsRepository;

@Component
public class MongoClientDetailsService implements ClientDetailsService {

	@Autowired
	private OauthClientDetailsRepository clientDetailsRepository;
	@Autowired
	private ClientDetailsAssembler assembler;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		OauthClientDetails details = clientDetailsRepository.findByClientId(clientId);
		if (details == null) {
			return null;
		}
		return assembler.assembleClientDetails(details);
	}

}