package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAuthenticatorAttachment;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AuthenticatorResponse;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticatorResponseServerRequestLv3<T extends AuthenticatorResponse> {
    @NotBlank
    private String id;
    @NotBlank
    private String rawId;
    @NotNull
    @Valid
    private T response;
    ServerAuthenticatorAttachment authenticatorAttachment;
    private Map<String, Object> clientExtensionResults;
    @NotBlank
    @Pattern(regexp = "public-key")
    private String type;
}
