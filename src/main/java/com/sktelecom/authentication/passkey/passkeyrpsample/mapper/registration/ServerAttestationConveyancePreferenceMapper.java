package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration;

import com.sktelecom.authentication.fido2.server.dto.registration.AttestationConveyancePreferenceDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAttestationConveyancePreference;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServerAttestationConveyancePreferenceMapper {
    ServerAttestationConveyancePreference toRpServerDto(AttestationConveyancePreferenceDto webauthnServer);
}
