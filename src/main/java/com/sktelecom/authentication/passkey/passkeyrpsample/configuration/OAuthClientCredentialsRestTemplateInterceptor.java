/*
 * Copyright (C) 2023 SK TELECOM CO., LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sktelecom.authentication.passkey.passkeyrpsample.configuration;

import static java.util.Objects.isNull;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

/**
 * OAuth2 Rest template interceptor for client credentials grant
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class OAuthClientCredentialsRestTemplateInterceptor implements ClientHttpRequestInterceptor {
    private static final String BEARER_PREFIX = "Bearer ";
    private final OAuth2AuthorizedClientManager manager;
    private final Authentication principal;
    private final ClientRegistration clientRegistration;

    public OAuthClientCredentialsRestTemplateInterceptor(OAuth2AuthorizedClientManager manager,
        ClientRegistration clientRegistration) {
        this.manager = manager;
        this.clientRegistration = clientRegistration;
        this.principal = createPrincipal();
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
        throws IOException {
        // @formatter:off
        OAuth2AuthorizeRequest oauth2AuthorizeRequest = OAuth2AuthorizeRequest
            .withClientRegistrationId(clientRegistration.getRegistrationId())
            .principal(principal)
            .build();
        // @formatter:on
        OAuth2AuthorizedClient client = manager.authorize(oauth2AuthorizeRequest);
        if (isNull(client)) {
            throw new IllegalStateException(
                "client credentials flow on " + clientRegistration.getRegistrationId() + " failed, client is null");
        }

        request.getHeaders().add(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + client.getAccessToken().getTokenValue());
        return execution.execute(request, body);
    }

    private Authentication createPrincipal() {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.emptySet();
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return this;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            }

            @Override
            public String getName() {
                return clientRegistration.getClientId();
            }
        };
    }
}
