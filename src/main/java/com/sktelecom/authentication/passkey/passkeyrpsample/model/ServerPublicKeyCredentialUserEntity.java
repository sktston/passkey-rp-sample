package com.sktelecom.authentication.passkey.passkeyrpsample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServerPublicKeyCredentialUserEntity {
    private String id;
    private String name;
    private String displayName;
}
