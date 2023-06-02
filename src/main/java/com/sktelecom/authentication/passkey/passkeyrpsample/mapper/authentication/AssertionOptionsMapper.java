package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.authentication;

import com.sktelecom.authentication.fido2.server.dto.authentication.PublicKeyCredentialRequestOptionsDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.common.ServerPublicKeyCredentialDescriptorMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.common.WebAuthnExtensionIdentifierMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionOptions;
import org.mapstruct.IterableMapping;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", uses = {
    ServerPublicKeyCredentialDescriptorMapper.class,
    WebAuthnExtensionIdentifierMapper.class
})
public interface AssertionOptionsMapper {
    @MapMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    AssertionOptions toRpServerDto(PublicKeyCredentialRequestOptionsDto webauthnServer);
}
