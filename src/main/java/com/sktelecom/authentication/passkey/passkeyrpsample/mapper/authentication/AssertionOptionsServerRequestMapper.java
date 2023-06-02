package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.authentication;

import com.sktelecom.authentication.fido2.server.dto.authentication.AuthenticationOptionsServerRequestDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionOptionsServerRequest;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.lv3.AssertionOptionsServerRequestLv3;
import com.sktelecom.authentication.passkey.passkeyrpsample.util.UserIdGeneratorUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssertionOptionsServerRequestMapper {
    @Mapping(target = "userId", source = "username")
    @Mapping(target = "timeout", constant = "300000L")
    AuthenticationOptionsServerRequestDto toWebauthnServerDto(AssertionOptionsServerRequest rpServer);

    @Mapping(target = "userId", source = "username")
    AuthenticationOptionsServerRequestDto toWebauthnServerDtoLv3(AssertionOptionsServerRequestLv3 rpServer);

    default String toWebauthnUserId(String username) {
        if (username == null) {
            return null;
        }
        return UserIdGeneratorUtil.generate(username);
    }
}
