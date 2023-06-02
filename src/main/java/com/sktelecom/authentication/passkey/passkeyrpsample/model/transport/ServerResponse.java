package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ServerResponse {
    private Status status;
    private String errorMessage;
}
