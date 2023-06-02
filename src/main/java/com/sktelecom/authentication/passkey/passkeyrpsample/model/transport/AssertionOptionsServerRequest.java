package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerUserVerificationRequirement;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssertionOptionsServerRequest {
    @NotBlank
    private String username;
    private ServerUserVerificationRequirement userVerification = ServerUserVerificationRequirement.PREFERRED;
    private Map<String, Object> extensions;
}
