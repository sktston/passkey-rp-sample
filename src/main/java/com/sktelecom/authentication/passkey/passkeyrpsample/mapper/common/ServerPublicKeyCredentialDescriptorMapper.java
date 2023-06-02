package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.common;

import com.sktelecom.authentication.fido2.server.dto.common.PublicKeyCredentialDescriptorDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerPublicKeyCredentialDescriptor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServerPublicKeyCredentialDescriptorMapper {
    ServerPublicKeyCredentialDescriptor toRpServerDto(PublicKeyCredentialDescriptorDto webauthnServer);
}
