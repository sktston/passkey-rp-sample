package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class AssertionOptionsServerResponse extends AssertionOptions {
    private Status status;
    @Builder.Default
    private String errorMessage = "";
}
