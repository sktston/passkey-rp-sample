package com.sktelecom.authentication.passkey.passkeyrpsample.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ServerAuthenticatorTransport {
    USB("usb"),
    NFC("nfc"),
    BLE("ble"),
    SMART_CARD("smart-card"),
    HYBRID("hybrid"),
    INTERNAL("internal");

    @Getter
    @JsonValue
    private final String value;

    private static final Map<String, ServerAuthenticatorTransport> MAP_BY_VALUE =
        Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(ServerAuthenticatorTransport::getValue, Function.identity())));

    public static ServerAuthenticatorTransport find(String value) {
        return Optional.ofNullable(MAP_BY_VALUE.get(value)).orElseThrow(NoSuchElementException::new);
    }
}
