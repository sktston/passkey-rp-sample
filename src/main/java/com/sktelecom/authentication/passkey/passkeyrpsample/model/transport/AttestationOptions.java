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

package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAttestationConveyancePreference;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAuthenticatorSelectionCriteria;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerPublicKeyCredentialDescriptor;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerPublicKeyCredentialParameters;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerPublicKeyCredentialRpEntity;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerPublicKeyCredentialUserEntity;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@SuperBuilder
public class AttestationOptions extends ServerOptions {
    private ServerPublicKeyCredentialRpEntity rp;
    private ServerPublicKeyCredentialUserEntity user;
    private String challenge;
    private List<ServerPublicKeyCredentialParameters> pubKeyCredParams;
    private Long timeout;
    private List<ServerPublicKeyCredentialDescriptor> excludeCredentials;
    private ServerAuthenticatorSelectionCriteria authenticatorSelection;
    private ServerAttestationConveyancePreference attestation;
    private Map<String, Object> extensions;
}
