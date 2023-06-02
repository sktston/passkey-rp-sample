package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerUserVerificationRequirement;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssertionOptionsServerRequestLv3 {
    private String username;
    private ServerUserVerificationRequirement userVerification;
    Long timeout;
}
