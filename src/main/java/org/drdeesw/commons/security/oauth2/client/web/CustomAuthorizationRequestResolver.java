/**
 * 
 */
package org.drdeesw.commons.security.oauth2.client.web;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;


/**
 * 
 */
public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver
{
  private static final Log log = LogFactory.getLog(CustomAuthorizationRequestResolver.class);
  private static final String                      CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";
  private static final String                      CLIENT_REQUEST_URI  = ".redirect-uri";

  private final OAuth2AuthorizationRequestResolver defaultResolver;
  @Autowired
  private Environment                              env;

  /**
   * @param clientRegistrationRepository
   * @param authorizationRequestBaseUri
   */
  public CustomAuthorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository,
                                            String authorizationRequestBaseUri)
  {
    this.defaultResolver = new DefaultOAuth2AuthorizationRequestResolver(
        clientRegistrationRepository, authorizationRequestBaseUri);
  }


  /**
   *
   */
  @Override
  public OAuth2AuthorizationRequest resolve(
    HttpServletRequest request)
  {
    OAuth2AuthorizationRequest authorizationRequest = defaultResolver.resolve(request);
    return customizeAuthorizationRequest(authorizationRequest);
  }


  /**
   *
   */
  @Override
  public OAuth2AuthorizationRequest resolve(
    HttpServletRequest request,
    String clientRegistrationId)
  {
    OAuth2AuthorizationRequest authorizationRequest = defaultResolver.resolve(request,
      clientRegistrationId);
    return customizeAuthorizationRequest(authorizationRequest);
  }


  /**
   * @param authorizationRequest
   * @return
   */
  private OAuth2AuthorizationRequest customizeAuthorizationRequest(
    OAuth2AuthorizationRequest authorizationRequest)
  {
    if (authorizationRequest == null)
    {
      return null;
    }

    OAuth2AuthorizationRequest.Builder builder = OAuth2AuthorizationRequest.from(authorizationRequest);
    String client = (String)authorizationRequest.getAttribute("registration_id");
    String redirectUri = env.getProperty(CLIENT_PROPERTY_KEY + client + CLIENT_REQUEST_URI);
    
    log.info("[customizeAuthorizationRequest] Custom Redirect URI: '" + redirectUri + "'");
    
    builder = builder.redirectUri(redirectUri);

    return builder.build();
  }
}
