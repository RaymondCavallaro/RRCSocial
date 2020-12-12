package social.teste.oauth2.server.mongo.conversor;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import social.teste.oauth2.server.mongo.entidade.OauthClientDetails;

@Component
public class ClientDetailsAssembler {

	@Autowired
	public ObjectMapper om;

	@Autowired
	private GrantedAuthorityAssembler grantedAuthorityAssembler;

	public ClientDetails assembleClientDetails(OauthClientDetails details) {
		return new ClientDetails() {

			@Override
			public boolean isSecretRequired() {
				return false;
			}

			@Override
			public boolean isScoped() {
				return false;
			}

			@Override
			public boolean isAutoApprove(String scope) {
				return "true".equals(details.getAutoapprove());
			}

			@Override
			public Set<String> getScope() {
				return details.getScope();
			}

			@Override
			public Set<String> getResourceIds() {
				return details.getResourceIds();
			}

			@Override
			public Set<String> getRegisteredRedirectUri() {
				return details.getWebServerRedirectUri();
			}

			@Override
			public Integer getRefreshTokenValiditySeconds() {
				return details.getRefreshTokenValidity();
			}

			@Override
			public String getClientSecret() {
				return details.getClientSecret();
			}

			@Override
			public String getClientId() {
				return details.getClientId();
			}

			@Override
			public Set<String> getAuthorizedGrantTypes() {
				return details.getAuthorizedGrantTypes();
			}

			@Override
			public Collection<GrantedAuthority> getAuthorities() {
				return grantedAuthorityAssembler.assembleGrantedAuthoritiesFromCollection(details.getAuthorities());
			}

			@Override
			public Map<String, Object> getAdditionalInformation() {
				try {
					return om.readValue(details.getAdditionalInformation(), Map.class);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}

			@Override
			public Integer getAccessTokenValiditySeconds() {
				return details.getAccessTokenValidity();
			}
		};
	}

}