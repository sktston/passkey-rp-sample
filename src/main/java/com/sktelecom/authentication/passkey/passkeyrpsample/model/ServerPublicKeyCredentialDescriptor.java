package com.sktelecom.authentication.passkey.passkeyrpsample.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServerPublicKeyCredentialDescriptor {
    private final String type = "public-key";
    private String id;
    private List<ServerAuthenticatorTransport> transports;
}
