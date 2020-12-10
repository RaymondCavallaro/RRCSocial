package social.teste.oauth2.server.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import social.teste.resource.server.repository.MensagemRepository;

@Component
public class MongoClientDetailsService implements ClientDetailsService {

	@Autowired
	private MensagemRepository mensagemRepository;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		// TODO Auto-generated method stub
		return null;
	}

}