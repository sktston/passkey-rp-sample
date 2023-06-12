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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sktelecom.authentication.passkey.passkeyrpsample.configuration.WebSecurityConfig;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerUserVerificationRequirement;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.WebAuthnServerResponse;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.AssertionOptions;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.Status;
import com.sktelecom.authentication.passkey.passkeyrpsample.service.AssertionService;
import javax.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@Import({WebSecurityConfig.class})
@WebMvcTest(controllers = AssertionController.class)
class AssertionControllerTest extends MockMvcControllerBase {
    @MockBean
    private AssertionService assertionService;

    @Autowired
    public AssertionControllerTest(MockMvc mockMvc, AssertionService assertionService) {
        super(mockMvc);
        this.assertionService = assertionService;
    }

    @Test
    void getAssertionOptions() throws Exception {
        // @formatter:off
        when(assertionService.getOptions(any()))
            .thenReturn(new WebAuthnServerResponse<>(
                "8c37fdb6-a8b6-4d47-aa11-893dda02a5ae",
                deserializeFromFile("json/assertion_options.json", AssertionOptions.class)));

        mockMvc.perform(post("/assertion/options")
                .contentType(MediaType.APPLICATION_JSON)
                .content(fromFile("json/attestation_options_server_req.json")))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(cookie().value("assertion_request_id", "8c37fdb6-a8b6-4d47-aa11-893dda02a5ae"))
            .andExpect(jsonPath("$.status").value(Status.OK.getValue()))
            .andExpect(jsonPath("$.errorMessage").value(""))
            .andExpect(jsonPath("$.challenge").value("6283u0svT-YIF3pSolzkQHStwkJCaLKx"))
            .andExpect(jsonPath("$.timeout").value(20000))
            .andExpect(jsonPath("$.rpId").value("example.com"))
            .andExpect(jsonPath("$.allowCredentials", hasSize(1)))
            .andExpect(jsonPath("$.allowCredentials[0].id").value("m7xl_TkTcCe0WcXI2M-4ro9vJAuwcj4m"))
            .andExpect(jsonPath("$.allowCredentials[0].type").value("public-key"))
            .andExpect(jsonPath("$.userVerification").value(ServerUserVerificationRequirement.REQUIRED.getValue()));
        // @formatter:on
    }

    @Test
    void handleAssertionResult() throws Exception {
        doNothing().when(assertionService).handleResult(any(), any());
        // @formatter:off
        mockMvc.perform(post("/assertion/result")
                .contentType(MediaType.APPLICATION_JSON)
                .content(fromFile("json/assertion_result_server_req.json"))
                .cookie(new Cookie("assertion_request_id", "8c37fdb6-a8b6-4d47-aa11-893dda02a5ae")))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value(Status.OK.getValue()))
            .andExpect(jsonPath("$.errorMessage").value(""));
        // @formatter:on
    }
}