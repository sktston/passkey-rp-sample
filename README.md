# Passkey Sample Relying Party Application

## Introduction

This repository is for sample [WebAuthn Relying Party (RP)](https://w3c.github.io/webauthn/#webauthn-relying-party) web application based on the Spring Boot Framework.
This application does rely on the [SK telecom's Passkey Platform Server](https://passkey.daplatform.kr/docs/api.html) to implement WebAuthn (FIDO2) and Passkey features.
By design, most of the features are implemented in the [SK telecom's Passkey Platform Server](https://passkey.daplatform.kr/docs/api.html) and RPs simply introduce the features by integrating it.

We have designed and implemented this application so that any RP developers can understand the way how to integrate with [SK telecom's Passkey Platform](https://passkey.daplatform.kr/docs/api.html) easily.
> **IMPORTANT**: You need to carefully design and implement WebAuthn and Passkey integration if you plan to introduce them to your real products.
> Again, this sample is for demonstration and testing purpose. By doing so, most of the registration, authentication options are populated in the client side,
> which is originally intended for backend server's job. You need to define your own registration, authentication policy for your cases and choose appropriate options for that policy. 
> 
> For the simplicity, this application des not have any persistent storage to manage users' WebAuthn credentials and there is no authenticated session management as well.

## Features

The RESTful APIs for this sample application is developed based on the [FIDO Alliance Conformance Test Tools](https://github.com/fido-alliance/conformance-test-tools-resources).
In addition to the APIs, this application also contains [FIDO Alliance Interop Web App](https://github.com/fido-alliance/fido2-interop-webapp).
You can load interop web app with following path on your root directory: `index.html`. If you run this sample application locally, you can load the web app here: `http://localhost:8081/index.html`.
If you would like to test the interop web app by yourself, of course, you need to set properties properly such as WebAuthn server information and OAuth2 Client configurations.
_You don't have to modify any codes for this interop web app. All you need to do it is to set such properties correctly._

### Native Application Supports

WebAuthn APIs are also available for native applications running on iOS, Android etc. The RP web application **MUST** declare that it owns and controls the native applications by including such associations.
In this sample application, the website contains the [such association](src/main/resources/static/.well-known/assetlinks.json) for SK telecom's sample Android application.
If you have your own Android application, you could simply replace values in `package_name` and `sha256_cert_fingerprints` with your own.

## Quick Start

This sample application uses Gradle-based build system. 

### Prerequisites

JDK11 and above.

### How to build the application

```shell
$ ./gradlew build
```

### How to run the application with _default_ profile

```shell
$ ./gradlew bootRun
```

### How to run the application with _active_ profiles

If you would like to load your own profile, you could additionally define your profile such as `application-dev.yml`.
For example, you can run the application with active `dev` profile with a following command.

```shell
$ ./gradlew bootRun --args='--spring.profiles.active=dev'
```

### How to run the tests

There are couples of controller tests.
```shell
$ ./gradlew test
```
Discover more commands with `./graldew tasks`.

## Properties (Configurations)

### Passkey Sample RP Server Properties

| Name                                             | Description                                                       | Default Value                                                               |
|--------------------------------------------------|-------------------------------------------------------------------|-----------------------------------------------------------------------------|
| conformance.base-url                             | Your (sample) web application base URL, for interop web app.      | `http://localhost:8081`                                                     |
| conformance.vendor-name                          | Display vendor name for the WebAuthn server, for interop web app. | `SK Telecom`                                                                |
| webauthn.rp.id                                   | [Relying Party (RP) ID](https://w3c.github.io/webauthn/#rp-id)    | `localhost`                                                                 |
| webauthn.server.api-version                      | WebAuthn Server API version.                                      | `v1`                                                                        |
| webauthn.server.base-url                         | WebAuthn Server Base URL, please refer target server url.         | `http://localhost:8080`                                                     |
| webauthn.server.url-path.registration-request    | Registration request endpoint.                                    | `/${webauthn.server.api-version}/registration/request`                      |
| webauthn.server.url-path.registration-response   | Registration response endpoint.                                   | `/${webauthn.server.api-version}/registration/response`                     |
| webauthn.server.url-path.authentication-request  | Authentication request endpoint.                                  | `/${webauthn.server.api-version}/authentication/request`                    |
| webauthn.server.url-path.authentication-response | Authentication response endpoint.                                 | `/${webauthn.server.api-version}/authentication/response`                   |
| webauthn.server.url-path.user                    | User endpoint.                                                    | `/${webauthn.server.api-version}/users/{userId}`                            |
| webauthn.server.url-path.user-credential         | User credential endpoint.                                         | `/${webauthn.server.api-version}/users/{userId}/credentials/{credentialId}` |
| webauthn.server.url-path.user-credentials        | Credentials associated to the user endpoint.                      | `/${webauthn.server.api-version}/users/{userId}/credentials`                |
| webauthn.server.oauth2-protected                 | Whether the WebAuthn server API protected with OAuth2             | `false`                                                                     |

### Spring OAuth2 Client Properties

If WebAuthn server APIs (Passkey Platform Server) are protected with OAuth2 (`webauthn.server.oauth2-protected` property is `true`), you need to configure the OAuth2 Client information.
This sample application leverages Spring Security OAuth2.0 Client for the simplicity.
For more details, please refer the [Spring OAuth 2.0 Client](https://docs.spring.io/spring-security/reference/reactive/oauth2/client/index.html).
Followings are the example OAuth2 Client configuration with Spring Security OAuth2.0 feature.

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          passkey-rp-scope:
            provider: sktelecom
            authorization-grant-type: client_credentials
            scope: passkey:rp
            client-id: your-client-id # replace here
            client-secret: your-client-secret # replace here
        provider:
          sktelecom:
            token-uri: https:/example.com/oauth2/token # set real oauth2 server token endpoint uri
```

Note that you **MUST** register your application to get `client_id` and `client_secret` for WebAuthn server API authorization.
For SK telecom Passkey Platform, the `client_id` **MUST** be identical to your [RP ID](https://w3c.github.io/webauthn/#rp-id).

You would implement your own OAuth2 Client features instead of using Spring Security OAuth2 Client, and it's up to your choice.

## License

Passkey Sample Relying Party Application is Open Source software released under
the [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html).

## Contact Us

If you hae any other questions or concerns, please contact [kieun.shin@sk.com](mailto:kieun.shin@sk.com).