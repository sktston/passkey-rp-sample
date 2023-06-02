package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.authentication;

import com.sktelecom.authentication.fido2.server.dto.authentication.AssertionResponseDto;
import com.sktelecom.authentication.fido2.server.dto.common.PublicKeyCredentialDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.mapper.common.PublicKeyCredentialTypeMapper;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AuthenticatorResponseServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AuthenticatorResponseServerRequestLv3;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    uses = {
        AssertionResponseMapper.class,
        PublicKeyCredentialTypeMapper.class
    },
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AuthenticatorAssertionResponseServerRequestMapper {
    @Mapping(target = "clientExtensionResults", source = "getClientExtensionResults")
    PublicKeyCredentialDto<AssertionResponseDto> toWebauthnServerDto(
        AuthenticatorResponseServerRequest<AssertionResponse> rpServer);

    PublicKeyCredentialDto<AssertionResponseDto> toWebauthnServerDtoLv3(
        AuthenticatorResponseServerRequestLv3<AssertionResponse> rpServer);
}
