package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import com.sktelecom.authentication.fido2.server.dto.credential.CredentialInfoDto;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class GetUserCredentialsResponse extends ServerResponse {
    private List<CredentialInfoDto> credentials;
}
