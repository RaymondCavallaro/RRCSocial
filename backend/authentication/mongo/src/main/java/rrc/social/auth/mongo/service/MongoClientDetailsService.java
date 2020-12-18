package rrc.social.auth.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import rrc.social.auth.mongo.conversor.ClientDetailsAssembler;
import rrc.social.auth.mongo.entidade.OauthClientDetails;
import rrc.social.auth.mongo.repository.OauthClientDetailsRepository;

@Profile("mongo")
@Component("clientes")
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