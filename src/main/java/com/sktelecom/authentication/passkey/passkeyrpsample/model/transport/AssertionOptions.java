package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerPublicKeyCredentialDescriptor;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerUserVerificationRequirement;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@SuperBuilder
public class AssertionOptions extends ServerOptions {
    private String challenge;
    private Long timeout;
    private String rpId;
    @Builder.Default
    private List<ServerPublicKeyCredentialDescriptor> allowCredentials = Collections.emptyList();
    @Builder.Default
    private ServerUserVerificationRequirement userVerification = ServerUserVerificationRequirement.PREFERRED;
    private Map<String, Object> extensions;
}
