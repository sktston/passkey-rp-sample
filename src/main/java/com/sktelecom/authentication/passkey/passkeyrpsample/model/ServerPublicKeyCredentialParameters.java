package com.sktelecom.authentication.passkey.passkeyrpsample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServerPublicKeyCredentialParameters {
    private final String type = "public-key";
    private ServerCOSEAlgorithmIdentifier alg;
}
