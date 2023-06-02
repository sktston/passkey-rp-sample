package com.sktelecom.authentication.passkey.passkeyrpsample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServerPublicKeyCredentialRpEntity {
    private String name;
    private String id;
}
