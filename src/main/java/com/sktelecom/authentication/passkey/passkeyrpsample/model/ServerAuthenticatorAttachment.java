/*
 * Copyright (C) 2023 SK TELECOM CO., LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
public enum ServerAuthenticatorAttachment {
    PLATFORM("platform"),
    CROSS_PLATFORM("cross-platform");

    @Getter
    @JsonValue
    private final String value;

    private static final Map<String, ServerAuthenticatorAttachment> MAP_BY_VALUE =
        Collections.unmodifiableMap(Stream.of(values())
            .collect(Collectors.toMap(ServerAuthenticatorAttachment::getValue, Function.identity())));

    public static ServerAuthenticatorAttachment find(String value) {
        return Optional.ofNullable(MAP_BY_VALUE.get(value)).orElseThrow(NoSuchElementException::new);
    }
}
