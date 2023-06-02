package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration;

import com.sktelecom.authentication.fido2.server.dto.common.PublicKeyCredentialDto;
import com.sktelecom.authentication.fido2.server.dto.registration.AttestationResponseDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.common.PublicKeyCredentialTypeMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AuthenticatorResponseServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AttestationResponseLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AuthenticatorResponseServerRequestLv3;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    uses = {
        AttestationResponseMapper.class,
        PublicKeyCredentialTypeMapper.class
    },
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AuthenticatorAttestationResponseServerRequestMapper {
    @Mapping(target = "clientExtensionResults", source = "getClientExtensionResults")
    PublicKeyCredentialDto<AttestationResponseDto> toWebauthnServerDto(
        AuthenticatorResponseServerRequest<AttestationResponse> rpServer);

    PublicKeyCredentialDto<AttestationResponseDto> toWebauthnServerDtoLv3(
        AuthenticatorResponseServerRequestLv3<AttestationResponseLv3> rpServer);
}
