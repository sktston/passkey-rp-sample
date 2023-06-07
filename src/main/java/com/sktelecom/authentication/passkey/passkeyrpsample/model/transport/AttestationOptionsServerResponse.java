package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class AttestationOptionsServerResponse extends AttestationOptions {
    @Builder.Default
    private Status status = Status.OK;
    @Builder.Default
    private String errorMessage = "";
}
