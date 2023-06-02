package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAttestationConveyancePreference;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAuthenticatorSelectionCriteria;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerPublicKeyCredentialDescriptor;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerPublicKeyCredentialParameters;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerPublicKeyCredentialRpEntity;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerPublicKeyCredentialUserEntity;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@SuperBuilder
public class AttestationOptions extends ServerOptions {
    private ServerPublicKeyCredentialRpEntity rp;
    private ServerPublicKeyCredentialUserEntity user;
    private String challenge;
    private List<ServerPublicKeyCredentialParameters> pubKeyCredParams;
    private Long timeout;
    private List<ServerPublicKeyCredentialDescriptor> excludeCredentials;
    private ServerAuthenticatorSelectionCriteria authenticatorSelection;
    private ServerAttestationConveyancePreference attestation;
    private Map<String, Object> extensions;
}
