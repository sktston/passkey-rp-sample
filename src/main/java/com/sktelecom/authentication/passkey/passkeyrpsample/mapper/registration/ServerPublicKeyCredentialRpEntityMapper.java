package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration;

import com.sktelecom.authentication.fido2.server.dto.registration.RelyingPartyDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerPublicKeyCredentialRpEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServerPublicKeyCredentialRpEntityMapper {
    ServerPublicKeyCredentialRpEntity toRpServerDto(RelyingPartyDto webauthnServer);
}
