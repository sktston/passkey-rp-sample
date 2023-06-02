package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssertionResponse extends AuthenticatorResponse {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    @NotBlank
    private String clientDataJSON;
    @NotBlank
    private String authenticatorData;
    @NotBlank
    private String signature;
    private String userHandle;
}
