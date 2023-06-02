package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAttestationConveyancePreference;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAuthenticatorSelectionCriteria;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttestationOptionsServerRequestLv3 {
    @NotBlank
    private String username;
    @NotBlank
    private String displayName;
    private ServerAuthenticatorSelectionCriteria authenticatorSelection;
    private ServerAttestationConveyancePreference attestation;
    Boolean excludeCredentials;
    Long timeout;
}
