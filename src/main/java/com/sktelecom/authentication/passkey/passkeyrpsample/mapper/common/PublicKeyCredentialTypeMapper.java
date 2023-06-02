package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.common;

import com.sktelecom.authentication.fido2.server.dto.common.PublicKeyCredentialTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicKeyCredentialTypeMapper {
    default PublicKeyCredentialTypeDto toWebauthnServerDto(String type) {
        if (type == null) {
            return null;
        }
        return PublicKeyCredentialTypeDto.find(type);
    }
}
