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

@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
@AllArgsConstructor
public enum ServerCOSEAlgorithmIdentifier {
    RS1(-65535),
    RS256(-257),
    RS384(-258),
    RS512(-259),
    PS256(-37),
    PS384(-38),
    PS512(-39),
    ES256(-7),
    ES384(-35),
    ES512(-36),
    EdDSA(-8),  // https://datatracker.ietf.org/doc/html/rfc8152#section-8.2
    ES256K(-47);

    @JsonValue
    @Getter
    private final int value;

    private static final Map<Integer, ServerCOSEAlgorithmIdentifier> MAP_BY_VALUE =
        Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(ServerCOSEAlgorithmIdentifier::getValue, Function.identity())));

    public static ServerCOSEAlgorithmIdentifier find(int value) {
        return Optional.ofNullable(MAP_BY_VALUE.get(value)).orElseThrow(NoSuchElementException::new);
    }
}
