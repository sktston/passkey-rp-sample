package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration;

import com.sktelecom.authentication.fido2.server.dto.registration.AttestationResponseDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttestationResponseMapper {
    AttestationResponseDto toWebauthnServerDto(AttestationResponse rpServer);
}
