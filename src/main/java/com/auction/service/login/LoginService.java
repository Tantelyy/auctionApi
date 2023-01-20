package com.auction.service.login;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public String generateToken(){
       byte[] randomBytes = new byte[10];
       secureRandom.nextBytes(randomBytes);
       return base64Encoder.encodeToString(randomBytes);
    }
}
