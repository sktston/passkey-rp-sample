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

package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAuthenticatorTransport;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AuthenticatorResponse;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttestationResponseLv3 extends AuthenticatorResponse {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    @NotBlank
    private String clientDataJSON;
    @NotBlank
    private String attestationObject;
    List<ServerAuthenticatorTransport> transports;
}
