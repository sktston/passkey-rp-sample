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
