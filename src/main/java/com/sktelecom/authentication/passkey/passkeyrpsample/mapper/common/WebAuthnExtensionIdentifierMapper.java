package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.common;

import com.sktelecom.authentication.fido2.server.dto.common.WebAuthnExtensionIdentifierDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebAuthnExtensionIdentifierMapper {
    default String toRpServerDto(WebAuthnExtensionIdentifierDto webauthnServer) {
        if (webauthnServer == null) {
            return null;
        }
        return webauthnServer.getValue();
    }
}
