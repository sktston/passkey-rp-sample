package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration;

import com.sktelecom.authentication.fido2.server.dto.registration.PublicKeyCredentialParametersDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerPublicKeyCredentialParameters;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServerPublicKeyCredentialParametersMapper {
    ServerPublicKeyCredentialParameters toRpServerDto(PublicKeyCredentialParametersDto webauthnServer);
}
