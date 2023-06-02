package com.sktelecom.authentication.passkey.passkeyrpsample.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * A utility for webauthn RP user id generation based on the hash function
 */
public class UserIdGeneratorUtil {
    private static final MessageDigest digest;

    private UserIdGeneratorUtil() {
    }

    static {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generate(String username) {
        byte[] hash = digest.digest(username.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
    }
}
