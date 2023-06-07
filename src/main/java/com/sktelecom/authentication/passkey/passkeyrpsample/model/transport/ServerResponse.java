package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Data
@SuperBuilder
public class ServerResponse {
    @Builder.Default
    private Status status = Status.OK;
    @Builder.Default
    private String errorMessage = "";
}
