package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import com.sktelecom.authentication.fido2.server.dto.credential.CredentialInfoDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class GetUserCredentialResponse extends ServerResponse {
    private CredentialInfoDto credential;
}
