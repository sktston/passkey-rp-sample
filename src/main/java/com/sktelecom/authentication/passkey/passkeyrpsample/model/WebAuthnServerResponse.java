package com.sktelecom.authentication.passkey.passkeyrpsample.model;

import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.ServerOptions;
import lombok.Value;

@Value
public class WebAuthnServerResponse<T extends ServerOptions> {
    String sessionId;
    T options;
}
