package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.authentication;

import com.sktelecom.authentication.fido2.server.dto.authentication.AssertionResponseDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AssertionResponseMapper {
    AssertionResponseDto toWebauthnServerDto(AssertionResponse rpServer);
}
