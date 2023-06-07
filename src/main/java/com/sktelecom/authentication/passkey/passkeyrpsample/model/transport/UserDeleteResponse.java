package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UserDeleteResponse extends ServerResponse {
    private List<String> credentials;
}
