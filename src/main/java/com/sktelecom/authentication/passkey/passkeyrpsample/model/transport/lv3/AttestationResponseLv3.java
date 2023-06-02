package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAuthenticatorTransport;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AuthenticatorResponse;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttestationResponseLv3 extends AuthenticatorResponse {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    @NotBlank
    private String clientDataJSON;
    @NotBlank
    private String attestationObject;
    List<ServerAuthenticatorTransport> transports;
}
