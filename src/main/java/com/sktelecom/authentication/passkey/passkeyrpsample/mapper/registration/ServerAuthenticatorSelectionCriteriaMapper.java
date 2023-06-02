package com.sktelecom.authentication.passkey.passkeyrpsample.mapper.registration;

import com.sktelecom.authentication.fido2.server.dto.registration.AuthenticatorSelectionCriteriaDto;
import com.sktelecom.authentication.fido2.server.dto.registration.ResidentKeyRequirementDto;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAuthenticatorSelectionCriteria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServerAuthenticatorSelectionCriteriaMapper {
    @Mapping(target = "requireResidentKey", source = "residentKey")
    ServerAuthenticatorSelectionCriteria toRpServerDto(AuthenticatorSelectionCriteriaDto webauthnServer);

    @Mapping(target = "residentKey", source = ".")
    AuthenticatorSelectionCriteriaDto toWebauthnServerDto(ServerAuthenticatorSelectionCriteria rpServer);

    default boolean toWebauthnRequireResidentKey(ResidentKeyRequirementDto residentKey) {
        if (residentKey == null) {
            return false;
        }
        return residentKey == ResidentKeyRequirementDto.REQUIRED;
    }

    default ResidentKeyRequirementDto getResidentKey(ServerAuthenticatorSelectionCriteria rpServer) {
        if (rpServer == null) {
            return null;
        }

        if (null != rpServer.getResidentKey()) {
            return ResidentKeyRequirementDto.find(rpServer.getResidentKey().getValue());
        }

        if (rpServer.isRequireResidentKey()) {
            return ResidentKeyRequirementDto.REQUIRED;
        }

        return null;
    }
}
