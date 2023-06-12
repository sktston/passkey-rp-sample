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

package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration;

import com.sktelecom.authentication.fido2.server.dto.registration.PublicKeyCredentialCreationOptionsDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.common.ServerPublicKeyCredentialDescriptorMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.common.WebAuthnExtensionIdentifierMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationOptions;
import org.mapstruct.IterableMapping;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", uses = {
    ServerPublicKeyCredentialRpEntityMapper.class,
    ServerPublicKeyCredentialUserEntityMapper.class,
    ServerPublicKeyCredentialParametersMapper.class,
    ServerPublicKeyCredentialDescriptorMapper.class,
    ServerAuthenticatorSelectionCriteriaMapper.class,
    ServerAttestationConveyancePreferenceMapper.class,
    WebAuthnExtensionIdentifierMapper.class
})
public interface AttestationOptionsMapper {
    @MapMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    AttestationOptions toRpServerDto(PublicKeyCredentialCreationOptionsDto webauthnServer);
}
