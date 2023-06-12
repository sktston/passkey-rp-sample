/*
 * Copyright (C) 2023 SK TELECOM CO., LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sktelecom.authentication.passkey.passkeyrpsample.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sktelecom.authentication.passkey.passkeyrpsample.configuration.WebSecurityConfig;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAttestationConveyancePreference;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerAuthenticatorAttachment;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerUserVerificationRequirement;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.WebAuthnServerResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AttestationOptions;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.Status;
import com.sktelecom.authentication.passkey.passkeyrpsample.service.AttestationService;
import javax.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@Import({WebSecurityConfig.class})
@WebMvcTest(controllers = AttestationController.class)
class AttestationControllerTest extends MockMvcControllerBase {
    @MockBean
    private AttestationService attestationService;

    @Autowired
    public AttestationControllerTest(MockMvc mockMvc, AttestationService attestationService) {
        super(mockMvc);
        this.attestationService = attestationService;
    }

    @Test
    void getAttestationOptions() throws Exception {
        // @formatter:off
        when(attestationService.getOptions(any()))
            .thenReturn(new WebAuthnServerResponse<>(
                "43eb3a7f-766b-4028-88da-8f5baf767732",
                deserializeFromFile("json/attestation_options.json", AttestationOptions.class)));

        mockMvc.perform(post("/attestation/options")
                .contentType(MediaType.APPLICATION_JSON)
                .content(fromFile("json/attestation_options_server_req.json")))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(cookie().value("attestation_request_id", "43eb3a7f-766b-4028-88da-8f5baf767732"))
            .andExpect(jsonPath("$.status").value(Status.OK.getValue()))
            .andExpect(jsonPath("$.errorMessage").value(""))
            .andExpect(jsonPath("$.rp.name").value("Example Corporation"))
            .andExpect(jsonPath("$.user.name").value("johndoe@example.com"))
            .andExpect(jsonPath("$.user.displayName").value("John Doe"))
            .andExpect(jsonPath("$.challenge").value("uhUjPNlZfvn7onwuhNdsLPkkE5Fv-lUN"))
            .andExpect(jsonPath("$.timeout").value(10000))
            .andExpect(jsonPath("$.authenticatorSelection.requireResidentKey").value(false))
            .andExpect(jsonPath("$.authenticatorSelection.authenticatorAttachment").value(ServerAuthenticatorAttachment.CROSS_PLATFORM.getValue()))
            .andExpect(jsonPath("$.authenticatorSelection.userVerification").value(ServerUserVerificationRequirement.PREFERRED.getValue()))
            .andExpect(jsonPath("$.attestation").value(ServerAttestationConveyancePreference.DIRECT.getValue()));
        // @formatter:on
    }

    @Test
    void handleAttestationResult() throws Exception {
        doNothing().when(attestationService).handleResult(any(), any());
        // @formatter:off
        mockMvc.perform(post("/attestation/result")
                .contentType(MediaType.APPLICATION_JSON)
                .content(fromFile("json/attestation_result_server_req.json"))
                .cookie(new Cookie("attestation_request_id", "43eb3a7f-766b-4028-88da-8f5baf767732")))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value(Status.OK.getValue()))
            .andExpect(jsonPath("$.errorMessage").value(""));
        // @formatter:on
    }
}