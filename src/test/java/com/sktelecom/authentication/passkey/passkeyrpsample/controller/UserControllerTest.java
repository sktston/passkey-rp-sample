package com.sktelecom.authentication.passkey.passkeyrpsample.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sktelecom.authentication.passkey.passkeyrpsample.configuration.WebSecurityConfig;
import com.sktelecom.authentication.passkey.passkeyrpsample.model.transport.Status;
import com.sktelecom.authentication.passkey.passkeyrpsample.service.UserService;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@Import({WebSecurityConfig.class})
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest extends MockMvcControllerBase {
    @MockBean
    private UserService userService;

    @Autowired
    public UserControllerTest(MockMvc mockMvc, UserService userService) {
        super(mockMvc);
        this.userService = userService;
    }

    @Test
    void deleteUser() throws Exception {
        String givenUserId = "u_TtqmR8hucZGet9CcM6TREv031Bvsz6WKpo0ntBgDE";
        // @formatter:off
        when(userService.deleteUser(givenUserId))
            .thenReturn(Arrays.asList("KwiyRhxUYzJbrEVg9_XPYfjDUYCAwgV1_2fo7IwRRMo", "kINIXbwEY3WXYwP71tiGCbqGPoTgRfCYFWxopvDKTxc"));

        mockMvc.perform(delete("/users/{userId}", givenUserId))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value(Status.OK.getValue()))
            .andExpect(jsonPath("$.errorMessage").value(""))
            .andExpect(jsonPath("$.credentials").isArray())
            .andExpect(jsonPath("$.credentials", hasSize(2)))
            .andExpect(jsonPath("$.credentials", hasItem("KwiyRhxUYzJbrEVg9_XPYfjDUYCAwgV1_2fo7IwRRMo")))
            .andExpect(jsonPath("$.credentials", hasItem("kINIXbwEY3WXYwP71tiGCbqGPoTgRfCYFWxopvDKTxc")));
        // @formatter:on
    }

}
