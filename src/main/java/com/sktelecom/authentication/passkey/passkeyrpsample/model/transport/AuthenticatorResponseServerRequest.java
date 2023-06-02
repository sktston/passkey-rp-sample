package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticatorResponseServerRequest<T extends AuthenticatorResponse> {
    @NotBlank
    private String rawId;
    @NotBlank
    private String id;
    @NotBlank
    @Pattern(regexp = "public-key")
    private String type;
    private Map<String, Object> getClientExtensionResults;
    @NotNull
    @Valid
    private T response;
}
