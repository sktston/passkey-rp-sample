package com.sktelecom.authentication.passkey.passkeyrpsample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

public class MockMvcControllerBase {
    protected final MockMvc mockMvc;

    public MockMvcControllerBase(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    protected byte[] fromFile(String path) throws IOException {
        return StreamUtils.copyToByteArray(new ClassPathResource(path).getInputStream());
    }

    protected <T> T deserializeFromFile(String path, Class<T> valueType) throws IOException {
        InputStream inputStream = new ClassPathResource(path).getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, valueType);
    }
}
