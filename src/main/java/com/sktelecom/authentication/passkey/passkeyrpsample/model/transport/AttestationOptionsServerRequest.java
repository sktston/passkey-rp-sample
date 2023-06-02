package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAttestationConveyancePreference;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAuthenticatorSelectionCriteria;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttestationOptionsServerRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String displayName;
    private ServerAuthenticatorSelectionCriteria authenticatorSelection;
    private ServerAttestationConveyancePreference attestation = ServerAttestationConveyancePreference.NONE;
    private Map<String, Object> extensions;
}
