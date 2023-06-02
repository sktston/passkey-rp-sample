package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration;

import com.sktelecom.authentication.fido2.server.dto.registration.UserDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerPublicKeyCredentialUserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServerPublicKeyCredentialUserEntityMapper {
    ServerPublicKeyCredentialUserEntity toRpServerDto(UserDto webauthnServer);
}
