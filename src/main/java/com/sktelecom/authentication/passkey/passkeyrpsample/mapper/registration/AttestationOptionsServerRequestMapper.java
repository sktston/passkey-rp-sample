package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration;

import com.sktelecom.authentication.fido2.server.dto.registration.RegistrationOptionsServerRequestDto;
import com.sktelecom.authentication.fido2.server.dto.registration.UserDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationOptionsServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AttestationOptionsServerRequestLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.util.UserIdGeneratorUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
    ServerAuthenticatorSelectionCriteriaMapper.class
})
public interface AttestationOptionsServerRequestMapper {
    @Mapping(target = "user", source = "rpServer")
    @Mapping(target = "excludeCredentials", constant = "true")
    @Mapping(target = "timeout", constant = "300000L")
    RegistrationOptionsServerRequestDto toWebauthnServerDto(AttestationOptionsServerRequest rpServer);

    @Mapping(target = "user", source = "rpServer")
    RegistrationOptionsServerRequestDto toWebauthnServerDtoLv3(AttestationOptionsServerRequestLv3 rpServer);

    default UserDto toWebauthnUserDto(AttestationOptionsServerRequest rpServer) {
        if (rpServer == null || rpServer.getUsername() == null) {
            return null;
        }
        String userId = UserIdGeneratorUtil.generate(rpServer.getUsername());
        return new UserDto(userId, rpServer.getUsername(), rpServer.getDisplayName());
    }

    default UserDto toWebauthnUserDto(AttestationOptionsServerRequestLv3 rpServer) {
        if (rpServer == null || rpServer.getUsername() == null) {
            return null;
        }
        String userId = UserIdGeneratorUtil.generate(rpServer.getUsername());
        return new UserDto(userId, rpServer.getUsername(), rpServer.getDisplayName());
    }
}
