package com.sktelecom.authentication.passkey.passkeyrpsample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServerAuthenticatorSelectionCriteria {
    private ServerAuthenticatorAttachment authenticatorAttachment;
    private ServerResidentKeyRequirement residentKey;
    private boolean requireResidentKey;
    private ServerUserVerificationRequirement userVerification;
}
