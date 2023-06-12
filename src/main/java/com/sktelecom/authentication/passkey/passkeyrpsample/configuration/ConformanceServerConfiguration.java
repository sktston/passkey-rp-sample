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

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInitializer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@RequiredArgsConstructor
public class ConformanceServerConfiguration {
    private final WebAuthnProperties webauthnProperties;

    @Bean
    ObjectMapper objectMapper() {
        // create custom object mapper to use properties based deserialization
        return new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new JavaTimeModule())
            .setSerializationInclusion(Include.NON_NULL)
            .setConstructorDetector(ConstructorDetector.USE_PROPERTIES_BASED);
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeHeaders(true);
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeClientInfo(true);
        filter.setMaxPayloadLength(1000);
        return filter;
    }

    /**
     * Rest template without oauth2 features
     * @return rest template
     */
    @ConditionalOnMissingBean(RestTemplate.class)
    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // customize rest template for WebAuthn server
        customizeRestTemplate(restTemplate);
        return restTemplate;
    }

    /**
     * Rest template for oauth2 protected resource server
     * @param authorizedClientManager oauth2 authorized client manager
     * @param clientRegistrationRepository oauth2 client registration repository
     * @return rest template with oauth2 client feature
     */
    @Primary
    @ConditionalOnProperty(prefix = "webauthn.server", value = "oauth2-protected", havingValue = "true")
    @Bean("oauthRestTemplate")
    public RestTemplate oauthRestTemplate(OAuth2AuthorizedClientManager authorizedClientManager,
        ClientRegistrationRepository clientRegistrationRepository) {
        // get client registration with registration id for "passkey-rp-scope" scope
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("passkey-rp-scope");

        // add interceptor to inject oauth2 access token in to the authorization header
        // @formatter:off
        RestTemplate restTemplate = new RestTemplateBuilder()
            .additionalInterceptors(
                new OAuthClientCredentialsRestTemplateInterceptor(authorizedClientManager, clientRegistration))
            .setReadTimeout(Duration.ofSeconds(5))
            .setConnectTimeout(Duration.ofSeconds(1))
            .build();
        // @formatter:on

        // customize rest template for WebAuthn server
        customizeRestTemplate(restTemplate);

        return restTemplate;
    }

    @Bean
    ClientHttpRequestInitializer webauthnClientHttpRequestInitializer() {
        return new WebAuthnClientHttpRequestInitializer(this.webauthnProperties);
    }

    private void customizeRestTemplate(RestTemplate restTemplate) {
        // inject custom jackson message converter
        restTemplate.getMessageConverters().add(0, createMappingJacksonHttpMessageConverter());
        // inject custom rest client request initializer to set rp id header
        restTemplate.getClientHttpRequestInitializers().add(0, webauthnClientHttpRequestInitializer());
    }

    private MappingJackson2HttpMessageConverter createMappingJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }
}
