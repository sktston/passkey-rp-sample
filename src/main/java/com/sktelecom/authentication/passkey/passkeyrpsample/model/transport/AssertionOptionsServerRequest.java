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

package com.sktelecom.authentication.passkey.passkeyrpsample.model.transport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.ServerUserVerificationRequirement;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssertionOptionsServerRequest {
    @NotBlank
    private String username;
    private ServerUserVerificationRequirement userVerification = ServerUserVerificationRequirement.PREFERRED;
    private Map<String, Object> extensions;
}
