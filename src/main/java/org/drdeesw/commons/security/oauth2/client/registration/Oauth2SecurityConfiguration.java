package org.drdeesw.commons.security.oauth2.client.registration;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.drdeesw.commons.security.config.oauth2.client.UncommonOAuth2Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;


/**
 * This is where to get the OAuth2 ClientRegistrationRepository.
 */
public abstract class Oauth2SecurityConfiguration
{
  private static final String   CLIENT_ID           = ".client-id";
  private static final String   CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";
  private static final String   CLIENT_SCOPE        = ".scope";
  private static final String   CLIENT_SECRET       = ".client-secret";
  private static final String   CLIENTS_KEY         = "security.oauth2.client.registration.clients";
  protected static final String COINBASE_CLIENT     = "coinbase";
  protected static final String FACEBOOK_CLIENT     = "facebook";
  protected static final String GITHUB_CLIENT       = "github";
  protected static final String GOOGLE_CLIENT       = "google";
  protected static final String OKTA_CLIENT         = "okta";
  @Autowired
  private Environment           env;

  /**
   * 
   */
  protected Oauth2SecurityConfiguration()
  {

  }


  /**
   * For unit testing.
   * 
   * @param env
   */
  public Oauth2SecurityConfiguration(Environment env)
  {
    this.env = env;
  }


  /**
   * @return
   */
  @Bean
  protected OAuth2AuthorizedClientService authorizedClientService()
  {
    return newOAuth2AuthorizedClientService(clientRegistrationRepository());
  }


  /**
   * Returns a new ClientRegistrationRepository based on the clients defined by the "security.oauth2.client.registration.clients" property.
   * 
   * @return a new ClientRegistrationRepository based on the clients defined by the "security.oauth2.client.registration.clients" property
   */
  @Bean
  protected ClientRegistrationRepository clientRegistrationRepository()
  {
    String clientsValue = env.getProperty(CLIENTS_KEY);
    List<String> clients = Arrays.asList(clientsValue.split(","));
    List<ClientRegistration> registrations = clients.stream()//
        .map(client -> getRegistration(client))//
        .filter(registration -> registration != null)//
        .collect(Collectors.toList());

    return newClientRegistrationRepository(registrations);
  }


  /**
   * Returns the registration for the given client.
   * 
   * @param client "coinbase" or "google", etc.
   * @return the registration for the given client.
   */
  private ClientRegistration getRegistration(
    String client)
  {
    String clientId = env.getProperty(CLIENT_PROPERTY_KEY + client + CLIENT_ID);

    if (clientId == null)
    {
      return null;
    }

    String clientSecret = env.getProperty(CLIENT_PROPERTY_KEY + client + CLIENT_SECRET);

    if (client.equals(GOOGLE_CLIENT))
    {
      return CommonOAuth2Provider.GOOGLE.getBuilder(client)//
          .clientId(clientId)//
          .clientSecret(clientSecret)//
          .scope(CLIENT_SCOPE)//
          .build();
    }
    else if (client.equals(GITHUB_CLIENT))
    {
      return CommonOAuth2Provider.GITHUB.getBuilder(client)//
          .clientId(clientId)//
          .clientSecret(clientSecret)//
          .build();
    }
    else if (client.equals(FACEBOOK_CLIENT))
    {
      return CommonOAuth2Provider.FACEBOOK.getBuilder(client)//
          .clientId(clientId)//
          .clientSecret(clientSecret)//
          .build();
    }
    else if (client.equals(OKTA_CLIENT))
    {
      return CommonOAuth2Provider.OKTA.getBuilder(client)//
          .clientId(clientId)//
          .clientSecret(clientSecret)//
          .build();
    }
    else if (client.equals(COINBASE_CLIENT))
    {
      return UncommonOAuth2Provider.COINBASE.getBuilder(client)//
          .clientId(clientId)//
          .clientSecret(clientSecret)//
          .build();
    }
    else
      return null;
  }


  /**
   * Returns the session bearer token, if it exists, null otherwise.
   * 
   * @return the session bearer token, if it exists, null otherwise
   */
  public String getSessionBearerToken()
  {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication.isAuthenticated() && authentication instanceof OAuth2AuthenticationToken)
    {
      OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)authentication;
      String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
      String principalName = oauthToken.getName();
      OAuth2AuthorizedClient client = authorizedClientService()
          .loadAuthorizedClient(clientRegistrationId, principalName);

      DefaultOAuth2User x;
      
      //      log.info("[getSessionBearerToken] principal class="
      //               + oauthToken.getPrincipal().getClass().getSimpleName() + ", principalName="
      //               + principalName);

      return client == null ? null : client.getAccessToken().getTokenValue();
    }
    else
      return null;
  }


  /**
   * Returns a new instance of ClientRegistrationRepository; defaults to InMemoryClientRegistrationRepository
   *
   * @param registrations
   * @return a new instance of ClientRegistrationRepository; defaults to InMemoryClientRegistrationRepository
   */
  protected ClientRegistrationRepository newClientRegistrationRepository(
    List<ClientRegistration> registrations)
  {
    return new InMemoryClientRegistrationRepository(registrations);
  }


  /**
   * Returns a new instance of OAuth2AuthorizedClientService; defaults to InMemoryOAuth2AuthorizedClientService.
   * 
   * @param clientRegistrationRepository
   * @return a new instance of OAuth2AuthorizedClientService; defaults to InMemoryOAuth2AuthorizedClientService
   */
  protected OAuth2AuthorizedClientService newOAuth2AuthorizedClientService(
    ClientRegistrationRepository clientRegistrationRepository)
  {
    return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);

  }

}
